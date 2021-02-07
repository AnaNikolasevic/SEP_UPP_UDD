<template>
  <div>
    <h1>
      Choose beta-readers interested in the book genre whom you want to send the
      book for review:
    </h1>
    <!-- Snackbar -->
    <v-snackbar v-model="snackbarSuccess" :timeout="3500" top color="success">
      <span>{{ snackbarSuccessText }}</span>
      <v-btn text @click="snackbarSuccess = false">Close</v-btn>
    </v-snackbar>
    <v-snackbar v-model="snackbarDanger" :timeout="3500" top color="danger">
      <span>{{ snackbarDangerText }}</span>
      <v-btn text @click="snackbarDanger = false">Close</v-btn>
    </v-snackbar>
    <v-container>
      <v-layout row wrap>
        <v-flex
          xs12
          sm6
          md4
          lg10
          ml-12
          v-for="(formFieldsDTO, index) in bookPreviews"
          :key="index - 100"
        >
          <v-card hover elevation="2" class="text-center ma-6">
            <div class="detailsBorderColor">
              <v-card-title></v-card-title>
              <v-card-text>
                <div class="font-weight-bold headline">
                  <DefaultFormValues
                    @accepted="accept"
                    @denied="deny"
                    v-bind:formFieldsDTO="formFieldsDTO"
                    v-bind:pageName="pageName"
                  ></DefaultFormValues>
                </div>
              </v-card-text>
            </div>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import DefaultFormValues from "@/components/editor/DefaultFormValues.vue";
export default {
  components: {
    DefaultFormValues,
  },
  data() {
    return {
      snackbarSuccess: false,
      snackbarSuccessText: "",
      snackbarDanger: false,
      snackbarDangerText: "",
      bookPreviews: [],
      formFieldsDTO: [],
      pageName: "ChooseBetaReaders",
    };
  },
  methods: {
    getChooseBetaReaders() {
      axios
        .get(
          "http://localhost:8080/form/" +
            this.$store.state.user.username +
            "/" +
            "ChooseBetaReaders"
        )
        .then((response) => {
          this.bookPreviews = response.data;
          console.log(response.data);
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    accept(FormFieldsDTO, formFields) {
      let formSubmissionDto = new Array();
      formFields.forEach((formField) => {
        if (formField.type.name != "string") {
          if (formField.type.name == "boolean") {
            formField.fieldValue = true;
          }
          formSubmissionDto.push({
            id: formField.id,
            fieldValue: formField.fieldValue,
          });
        }
      });
      console.log(formSubmissionDto);
      this.submitForm(formSubmissionDto, FormFieldsDTO);
    },
    submitForm(formSubmissionDto, FormFieldsDTO) {
      console.log(formSubmissionDto);
      console.log(FormFieldsDTO);
      axios
        .post(
          "http://localhost:8080/subminForm/" +
            FormFieldsDTO.taskId +
            "/" +
            "form",
          formSubmissionDto
        )
        .then((response) => {
          this.close();
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
      this.$router.go(this.$router.currentRoute);
    },
  },
  mounted() {
    this.getChooseBetaReaders();
  },
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