<template>
  <v-row justify="center">       
    <v-dialog v-model="UploadDialog" max-width="600px">
      <v-card class="detailsBorderColor">
        <v-card-title>
          <span class="primary--text font-italic headline" primary-title
            >Upload</span
          >
          <v-spacer></v-spacer>
          <v-btn icon color="primary" @click="UploadDialog = false">
            <CloseIcon></CloseIcon>
          </v-btn>
        </v-card-title>  
        <v-row justify="center">
        <v-container>
            <v-form ref="form">      
          <FormComponent
            v-bind:formFields="formFields" >
          </FormComponent>
          </v-form>
        </v-container>
        </v-row>
        <v-card-actions class="pr-10 pb-10">
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="close">Cancel</v-btn>
          <v-btn color="primary" @click="upload()">Submit</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";
import CloseIcon from "vue-material-design-icons/CloseCircle.vue";
import FormComponent from "@/components/homePage/FormComponent.vue";
import firebase from 'firebase';
export default {
  components: {
    CloseIcon,
    FormComponent,
  },
  data() {
    return {
      UploadDialog: true,
      snackbarSuccess: false,
      snackbarSuccessText: "",
      snackbarDanger: false,
      snackbarDangerText: "",
      token: "",
      ActivationDialog: true,
      formFields: [],
      taskId: "",
      imageData: null,
      uploadValue: 0,
      picture: null,
      formSubmissionDto: [],
    };
  },
  methods: { 
    loadUploadForm(){
         axios
        .get(
          "http://localhost:8080/form/" +
            this.$store.state.user.username
        )
        .then((response) => {
          this.formFields = response.data[0].formFields;
          this.taskId = response.data[0].taskId;
          console.log("Usaooo u responseee");
          console.log(response.data);
        })
        .catch((error) => {
          alert("You don't have permission to access this action.");
          console.log(error);
        });
    },
    upload(){
        if (this.formFields[0].type.name == "file_upload") {
            console.log("usaooo u if")
            var imageData = this.formFields[0].fieldValue;    
            const storageRef=firebase.storage().ref(`${imageData.name}`).put(imageData);
            storageRef.on(`state_changed`,snapshot=>{
                this.uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes)*100;
                }, error=>{console.log(error.message)},
                ()=>{this.uploadValue=100;
                    storageRef.snapshot.ref.getDownloadURL().then((url)=>{
                    console.log("ovde ispiusujem url")
                    console.log(url);
                    this.formSubmissionDto.push ({
                        id: this.formFields[0].id,
                        fieldValue: url,
                        }); 
                    this.submitUploadForm(); 
                    
                    });
                }
            );
        } else {
            this.submitCheckboxForm();
        }
    },
    submitUploadForm(){
      console.log("submit");
      console.log(this.formSubmissionDto);
      console.log(this.taskId);     
      
      axios
        .post(
          "http://localhost:8080/subminForm/" +
            this.taskId +
            "/" +
            "mandatoryBooksPaths",
          this.formSubmissionDto
        )
        .then((response) => {

          location.reload();
          console.log(response);
          this.getBookPreviews();
          
        })
        .catch((error) => {
          console.log(error);
        });
    },
    submitCheckboxForm(){
      this.formSubmissionDto.push ({
        id: this.formFields[0].id,
        fieldValue: this.formFields[0].fieldValue,
      }); 
      console.log(this.formSubmissionDto);
      axios
      .post(
          "http://localhost:8080/subminForm/" +
            this.taskId +
            "/" +
            "checkbox",
            this.formSubmissionDto
        )
        .then((response) => {
          alert("If you checked YES, you can upload more books.\n If you checked NO, wait for board members decision.")
          this.close();
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    close() {
      this.UploadDialog = false;
      this.$refs.form.reset();
    },
  },
  mounted() {
    console.log(this.$store.state.processID);
    this.loadUploadForm();
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