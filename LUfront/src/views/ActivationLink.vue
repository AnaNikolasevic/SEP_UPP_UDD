<template>
  <v-row justify="center">
    <!-- snackbar -->
    <v-snackbar v-model="snackbarSuccess" :timeout="4000" top color="success">
      <span>{{ snackbarSuccessText }}</span>
      <v-btn text @click="snackbarSuccess = false">Close</v-btn>
    </v-snackbar>

    <v-snackbar v-model="snackbarDanger" :timeout="4000" top color="danger">
      <span>{{ snackbarDangerText }}</span>
      <v-btn text @click="snackbarDanger = false">Close</v-btn>
    </v-snackbar>

    <v-dialog v-model="ActivationDialog" max-width="600px">
      <div class="detailsBorderColor">
        <v-card>
          <v-card-title>
            <span class="primary--text font-italic headline" primary-title
              >Activate your account</span
            >
            <v-spacer></v-spacer>
            <v-btn icon color="primary" @click="ActivationDialog = false">
              <CloseIcon></CloseIcon>
            </v-btn>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-form ref="form">
                <v-btn
                  color="primary"
                  style="margin-left: 200px"
                  @click="activate"
                  >Activate</v-btn
                >
              </v-form>
            </v-container>
          </v-card-text>
        </v-card>
      </div>
    </v-dialog>
    <v-dialog v-model="UploadDialog" max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn text color="primary" v-on="on" @click="loadUploadForm()">
          <span>Upload</span>
          
        </v-btn>
      </template>
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
        <v-card-text>
          <v-container>
            <v-form ref="form">      
            <div v-for="field in formFields" :key="field.id">
            <v-text-field v-if="field.id=='bookName'"
                :label="field.id"
                v-model="field.fieldValue"
            ></v-text-field>
        </div>
            <input
              type="file"
              @change="previewImage"
              class="form-control"
              style="margin-left: 20px;"
            />
            <p>Progress: {{uploadValue.toFixed()+"%"}}
            <progress id="progress" :value="uploadValue" max="100" ></progress>  </p>
            </v-form>
          </v-container>
        </v-card-text>
        </v-row>
        <v-card-actions class="pr-10 pb-10">
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="close">Cancel</v-btn>
          <v-btn color="primary" @click="upload()">Add</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";
import CloseIcon from "vue-material-design-icons/CloseCircle.vue";
//import FormComponent from "@/components/homePage/FormComponent.vue";
import firebase from 'firebase';
export default {
  components: {
    CloseIcon,
    //FormComponent,
  },
  data() {
    return {
      UploadDialog: false,
      snackbarSuccess: false,
      snackbarSuccessText: "",
      snackbarDanger: false,
      snackbarDangerText: "",
      token: "",
      ActivationDialog: true,
      formFields: [],
      taskId: {},
      imageData: null,
      uploadValue: 0,
      picture: null,
    };
  },
  methods: {
    loadActivationForm() {},
    activate() {
      this.token = this.$route.params.token;
      console.log(this.token);
      console.log(this.$store.state.processID);
      axios
        .post(
          "http://localhost:8080/activation/" +
            this.token +
            "/" +
            this.$store.state.processID
        )
        .then((response) => {
          console.log(response.data);
          this.snackbarSuccess = true;
          this.snackbarSuccessText = "You have successfully registrated!";
          this.close();
          this.loadUploadForm();
        })
        .catch((error) => {
          this.poruka = "Doslo je do greske prilikom aktivacije vaseg naloga";
          console.log(error);
          this.snackbarDanger = true;
          this.snackbarDangerText = error;
          this.close();
        });
    },
    loadUploadForm(){
        axios.get("http://localhost:8080/uploadForm")
        .then((response) => {
          this.formFields = response.data.formFields;
          //this.$store.state.processID
          //this.$store.commit("addProcessID", response.data.processInstanceId);
          console.log(this.$store.state.processID );
          this.taskId = response.data.taskId;
          this.processInstanceId = response.data.processInstanceId;
          this.UploadDialog = true;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    previewImage(event) {
      this.uploadValue=0;
      this.picture=null;
      this.imageData = event.target.files[0];
    },

    upload(){
      this.picture=null;
      const storageRef=firebase.storage().ref(`${this.imageData.name}`).put(this.imageData);
      storageRef.on(`state_changed`,snapshot=>{
        this.uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes)*100;
      }, error=>{console.log(error.message)},
      ()=>{this.uploadValue=100;
        storageRef.snapshot.ref.getDownloadURL().then((url)=>{
          this.formFields[1].fieldValue = url;
          axios
            .post(
            "http://localhost:8080/upload/" + this.taskId,
            this.formFields
            )
            .then((response) => {
              console.log(response);
            })
            .catch((error) => {
              console.log(error);
            });
        });
      }
      );
    },
    close() {
      this.ActivationDialog = false;
      this.$refs.form.reset();
    },
  },
  mounted() {
    console.log(this.$store.state.processID);
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