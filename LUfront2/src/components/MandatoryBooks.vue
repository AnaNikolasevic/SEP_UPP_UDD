<template>
  <div>
    <v-snackbar v-model="snackbarSuccess" :timeout="3500" top color="success">
      <span>{{snackbarSuccessText}}</span>
      <v-btn text @click="snackbarSuccess = false">Close</v-btn>
    </v-snackbar>
    <v-snackbar v-model="snackbarDanger" :timeout="3500" top color="danger">
      <span>{{snackbarDangerText}}</span>
      <v-btn text @click="snackbarDanger = false">Close</v-btn>
    </v-snackbar>
    <v-container class="pt-12 mt-12">
      <v-row dense>
        <v-col
          v-for="(formFieldUnit, i) in formFields"
            :key="i"
          cols="12"
        >
          <v-card
          >
            <div class="d-flex flex-no-wrap justify-space-between">
              <div>
                <v-card-title
                ></v-card-title>
                <v-row justify="center">
                  <v-card-text>
                    <v-container>
                      <v-form ref="form">
                          <FormComponent v-bind:formFields="formFieldUnit.formFields"> </FormComponent>
                      </v-form>
                    </v-container>
                  </v-card-text>
                </v-row>
                <v-card-actions class="pr-10 pb-10">
                  <v-tooltip bottom color="white">
                    <template v-slot:activator="{ on }">
                      <v-btn icon v-on="on" color="black" @click="submitMandatoryBookEvaluationForm(formFieldUnit.formFields)" justify="end">
                        Vote
                      </v-btn>
                    </template>
                    <span class="black--text">Vote</span>
                  </v-tooltip>
                </v-card-actions>
              </div>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import FormComponent from "@/components/homePage/FormComponent.vue";
export default {
  components: {
    FormComponent,
  },
  data() {
    return {
      writers: [],
      mandatoryBook: {

      },
      snackbarSuccess: false,
      snackbarSuccessText: "",
      snackbarDanger: false,
      snackbarDangerText: "",
      taskId: "",
      formFields: [],
    };
  },
  methods: {
    getWriters() {
      axios
        .get("http://localhost:8080/writer")
        .then(response => {
          console.log(response);
          console.log(response.data);
          this.writers = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    loadMandatoryBookEvaluationForm() {
      axios.get("http://localhost:8080/form/" + this.$store.state.user.username + "/mandatoryBookEvaluationForm")
        .then((response) => {
          this.formFields = response.data;
          console.log(response);
          //this.$store.state.processID
          this.$store.commit("addProcessID", response.data.processInstanceId);
          console.log(this.$store.state.processID);
          this.taskId = response.data[0].taskId;
          this.processInstanceId = response.data.processInstanceId;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    submitMandatoryBookEvaluationForm(formFields) {
        let formSubmissionDto = new Array();
        formFields.forEach((formField) => {
          formSubmissionDto.push({
            id: formField.id,
            fieldValue: formField.fieldValue,
          });
        });
        axios.post(
          "http://localhost:8080/subminForm/" + this.taskId + "/boardMemberDecisions",
          formSubmissionDto
        )
          .then((response) => {
            alert("Done");
            location.reload();
            console.log(response);
          })
          .catch((error) => {
            alert("Error");
            console.log(error);
          });
    },
  },
  mounted() {
    this.getWriters();
    this.loadMandatoryBookEvaluationForm();
  }
};
</script>

<style>
.cardBorderColor {
  border-left: 1px solid #fbc02d;
  border-top: 1px solid #fbc02d;
  border-right: 1px solid #fbc02d;
  border-bottom: 1px solid #fbc02d;
}
.detailsBorderColor {
  border-left: 1.5px solid #fbc02d;
  border-top: 1.5px solid #fbc02d;
  border-right: 1.5px solid #fbc02d;
  border-bottom: 1.5px solid #fbc02d;
}
</style>