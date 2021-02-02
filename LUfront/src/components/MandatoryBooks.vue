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
      <v-row align="center">
         <v-expansion-panels :popout="true" :multiple="true" :focusable="true">
          <v-expansion-panel
            v-for="writer in writers"
            :key="writer.id"
            class="detailsBorderColor"
          >
            <v-expansion-panel-header>
              Writer {{ writer.firstName }} {{writer.lastName}}
            </v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-expansion-panels>
                <v-expansion-panel
                  v-for="mandatoryBook in writer.mandatoryBookList"
                  :key="mandatoryBook.id"
                  class="cardBorderColor"
                >
                  <v-expansion-panel-header>
                    <v-row no-gutters>
                      <v-col cols="4" class="primary--text">
                        Book "{{ mandatoryBook.title }}"
                      </v-col>
                      <v-col cols="4" class="primary--text">
                        <v-btn title="Open">                     
                            <a
                            :href="mandatoryBook.path"
                            >
                            <v-icon>mdi-file</v-icon>
                            </a>
                        </v-btn>
                      </v-col>
                    </v-row>
                  </v-expansion-panel-header>
                </v-expansion-panel>
                <v-container fluid >
                  <v-row>
                    <v-col cols="9"></v-col>
                    <v-col cols="1">
                      <v-tooltip bottom color="white">
                        <template v-slot:activator="{ on }">
                          <v-btn icon v-on="on" color="yellow" @click="submitMandatoryBookEvaluationForm()">
                            Vote
                          </v-btn>
                        </template>
                        <span class="yellow--text">Vote</span>
                      </v-tooltip>
                    </v-col>
                  </v-row>
                  <v-row justify="center">
                    <v-card-text>
                      <v-container>
                        <v-form ref="form">
                          <FormComponent v-bind:formFields="formFields"> </FormComponent>
                        </v-form>
                      </v-container>
                    </v-card-text>
                  </v-row>
                </v-container>
              </v-expansion-panels>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
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
    acceptRequest(comment) {
        comment.accepted = "true";
        console.log(comment.accepted);
      axios
        .put("/addvertisment-service/comment", comment)
        .then(response => {
          this.snackbarSuccess = true;
          this.snackbarSuccessText = "Comment is accepted!";
          console.log(response);
          this.getComments();
        })
        .catch(error => {
          this.snackbarDanger = true;
          this.snackbarDangerText = "Error";
          console.log(error);
        });
    },
    declineRequest(comment) {
        comment.accepted = "false";
      axios
        .put("/addvertisment-service/comment", comment)
        .then(response => {
          this.snackbarSuccess = true;
          this.snackbarSuccessText = "Comment is declined!";
          console.log(response);
          this.getComments();
        })
        .catch(error => {
          this.snackbarDanger = true;
          this.snackbarDangerText = "Error";
          console.log(error);
        });
    },
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
          this.formFields = response.data[0].formFields;
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
    submitMandatoryBookEvaluationForm() {
        let formSubmissionDto = new Array();
        this.formFields.forEach((formField) => {
          formSubmissionDto.push({
            id: formField.id,
            fieldValue: formField.fieldValue,
          });
        });
        axios.post(
          "http://localhost:8080/subminForm/" + this.taskId + "/boardMemberDecision",
          formSubmissionDto
        )
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
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