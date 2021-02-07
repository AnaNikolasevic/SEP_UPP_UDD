<template>
  <div>
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
                    v-bind:formFieldsDTO="formFieldsDTO"
                    v-bind:pageName="pageName"
                  ></DefaultFormValues>
                  <br/>
                  <v-btn color="primary" @click="publish(formFieldsDTO)">Publish</v-btn>
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
      pageName: "CheckBookPlagiarism",
      uploadValue: 0
      
    };
  },
  methods: {
    getBookPreviews() {
      axios
        .get(
          "http://localhost:8080/form/" +
            this.$store.state.user.username +
            "/" +
            "EditorApprovement"
        )
        .then((response) => {
          this.bookPreviews = response.data;
          console.log("Usaooo u responseee");
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    publish(formFieldsDTO){
        let formSubmissionDto = new Array();
        formSubmissionDto.push({
                id: "forPublish",
                fieldValue: true,
                });
        this.submitForm(formSubmissionDto, formFieldsDTO);      
                
    },
    submitForm(formSubmissionDto, FormFieldsDTO) {
      console.log("submit");
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
           this.getBookPreviews();
         })
         .catch((error) => {
           console.log(error);
         });
          this.$router.go(this.$router.currentRoute);
    },
  },

  mounted() {
    this.getBookPreviews();
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