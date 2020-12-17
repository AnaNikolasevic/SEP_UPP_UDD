<template>
  <v-row justify="center">
    <v-dialog v-model="RegisterDialog" max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn text color="primary" v-on="on" @click="loadRegistrationForm()">
          <span>Register</span>
          <RegistrationIcon></RegistrationIcon>
        </v-btn>
      </template>
      <v-card class="detailsBorderColor">
        <v-card-title>
          <span class="primary--text font-italic headline" primary-title
            >Registration</span
          >
          <v-spacer></v-spacer>
          <v-btn icon color="primary" @click="RegisterDialog = false">
            <CloseIcon></CloseIcon>
          </v-btn>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-form ref="form">
              <div v-for="field in formFields" :key="field.id">
                <v-text-field
                  :label="field.id"
                  v-if="field.type.name == 'string'"
                  v-model="field.fieldValue"
                  :rules="rules(field)"
                ></v-text-field>
                <v-combobox
                  v-if="field.id == 'userType'"
                  :items="Object.keys(field.type.values)"
                  :label="field.label"
                  v-model="field.fieldValue"
                  @change="changeGenreBetaVisibility(field.fieldValue)"
                  outlined
                  dense
                  :rules="rules(field)"
                ></v-combobox>
                <v-combobox
                  v-if="field.id == 'genre'"
                  :items="Object.keys(field.type.values)"
                  :label="field.label"
                  v-model="field.fieldValue"
                  outlined
                  dense
                  :rules="rules(field)"
                ></v-combobox>
                <v-combobox
                  v-if="field.id == 'genreBeta' && userType =='beta-reader'"
                  :items="Object.keys(field.type.values)"
                  :label="field.label"
                  v-model="field.fieldValue"
                  outlined
                  dense
                  :rules="rules(field)"
                ></v-combobox>
              </div>
            </v-form>
          </v-container>
        </v-card-text>
        <v-card-actions class="pr-10 pb-10">
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="close">Cancel</v-btn>
          <v-btn color="primary" @click="register">Register</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import Axios from "axios";
import RegistrationIcon from "vue-material-design-icons/AccountCircle.vue";
import CloseIcon from "vue-material-design-icons/CloseCircle.vue";
export default {
  components: {
    RegistrationIcon,
    CloseIcon,
  },
  data: () => ({
    RegisterDialog: false,
    user: {},
    confirmation: "",
    requiredRules: [(v) => !!v || "This field is required"],
    passwordRules: [
      (v) => !!v || "This is required",
      (v) => v == this.confirmation || "Passwords do not match",
    ],
    emailRules: [
      (v) => !!v || "This field is required",
      (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
    ],
    formFields: [],
    taskId: {},
    genreValues: [],
    selectedGenreValues: [],
    betaReader: false,
    field: {},
    userType: ""
  }),
  computed: {
    passwordConfirmationRule() {
      return () =>
        this.user.password === this.confirmation || "Password must match";
    },
  },
  methods: {
    changeGenreBetaVisibility(userType) {
      this.userType = userType;
    },
    rules(field){
      const rules = []
        field.validationConstraints.forEach(constraint => {
          if (constraint.name == 'required') {
            const rule = (v) => !!v || "This field is required"
            rules.push(rule)
          }
          if (constraint.name == 'minlength') {
            const rule = v => (v || '').length >= constraint.configuration ||
                    `A minimum of ${constraint.configuration} characters is required`
            rules.push(rule)
          }
          if (constraint.name == 'maxlength') {
            const rule = v => (v || '').length <= constraint.configuration ||
                    `A maximum of ${constraint.configuration} characters is allowed`
            rules.push(rule)
          }
         
        });
      return rules;
    },
    register() {
      if (this.$refs.form.validate()) {
        let formSubmissionDto = new Array();
        this.formFields.forEach((formField) => {
          formSubmissionDto.push({
            id: formField.id,
            fieldValue: formField.fieldValue,
          });
        });
        Axios.post(
          "http://localhost:8080/register/" + this.taskId,
          formSubmissionDto
        )
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
            console.log(error);
          });
        this.close();
      } else {
        console.log("nije validno");
      }
    },
    close() {
      this.RegisterDialog = false;
      this.$refs.form.reset();
    },
    loadRegistrationForm() {
      Axios.get("http://localhost:8080/registrationForm")
        .then((response) => {
          this.formFields = response.data.formFields;
          //this.$store.state.processID
          this.$store.commit("addProcessID", response.data.processInstanceId);
          console.log(this.$store.state.processID );
          this.taskId = response.data.taskId;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {},
};
</script>

<style scoped>
.cardBorderColor {
  border-left: 1px solid #9575cd;
  border-top: 1px solid #9575cd;
  border-right: 1px solid #9575cd;
  border-bottom: 1px solid #9575cd;
}
.detailsBorderColor {
  border-left: 2px solid #9575cd;
  border-top: 2px solid #9575cd;
  border-right: 2px solid #9575cd;
  border-bottom: 2px solid #9575cd;
}
</style>