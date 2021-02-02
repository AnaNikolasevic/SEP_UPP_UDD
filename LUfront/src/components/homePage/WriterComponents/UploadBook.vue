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
                  <v-btn color="primary" @click="upload(formFieldsDTO)">Upload</v-btn>
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
import firebase from 'firebase'
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
            "UploadBook"
        )
        .then((response) => {
          this.bookPreviews = response.data;
          console.log("Usaooo u responseee");
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    upload(FormFieldsDTO){
      console.log("usaooo u upload")
      let i = 0;
      for (i = 0; i <= FormFieldsDTO.formFields.length; i++) {
        console.log("usaooo u for")
        if (FormFieldsDTO.formFields[i].type.name == "file_upload") {
           console.log("usaooo u if")
          let formSubmissionDto = new Array();
          var imageData = FormFieldsDTO.formFields[i].fieldValue;    
          const storageRef=firebase.storage().ref(`${imageData.name}`).put(imageData);

      storageRef.on(`state_changed`,snapshot=>{
        this.uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes)*100;
      }, error=>{console.log(error.message)},
      ()=>{this.uploadValue=100;
        storageRef.snapshot.ref.getDownloadURL().then((url)=>{
          console.log("ovde ispiusujem url")
          console.log(url);
          formSubmissionDto.push({
            id: FormFieldsDTO.formFields[1].id,
            fieldValue: url,
            });   
          this.submitForm(formSubmissionDto, FormFieldsDTO); 
         
         
        });
      }
         );
       }
      }
    },
    submitForm(formSubmissionDto, FormFieldsDTO) {
      console.log("submit");
      console.log(formSubmissionDto);
      console.log(FormFieldsDTO.formFields);     
      
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