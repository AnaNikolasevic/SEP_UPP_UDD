<template>
  <v-row justify="center">
    <v-dialog v-model="AddBookDialog" max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn text color="primary" v-on="on" @click="loadBookForm()">
          <span>New Book</span>
         <v-icon right>mdi-plus</v-icon>
        </v-btn>
      </template>
      <v-card class="detailsBorderColor">
        <v-card-title>
          <span class="primary--text font-italic headline" primary-title
            >Add New Book</span
          >
          <v-spacer></v-spacer>
          <v-btn icon color="primary" @click="AddBookDialog = false">
            <CloseIcon></CloseIcon>
          </v-btn>
        </v-card-title>  
        <v-row justify="center">
        <v-card-text>
          <v-container>
            <v-form ref="form">      
          <FormComponent
            v-bind:formFields="formFields" >
          </FormComponent>
          </v-form>
          </v-container>
        </v-card-text>
    </v-row>
        <v-card-actions class="pr-10 pb-10">
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="close">Cancel</v-btn>
          <v-btn color="primary" @click="add()">Add</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>


<script>
import Axios from "axios";
import CloseIcon from "vue-material-design-icons/CloseCircle.vue";
import FormComponent from "@/components/homePage/FormComponent.vue";

export default {
  components: {
    CloseIcon,
    FormComponent,
  },
  data: () => ({
    AddBookDialog: false,
    user: {},
    confirmation: "",
    formFields: [],
    taskId: {},
  }),
  methods: {
    close() {
      this.RegisterDialog = false;
      this.$refs.form.reset();
    },
    loadBookForm() {
      Axios.get("http://localhost:8080/getForm/" + 'bookPublishing')
        .then((response) => {
          this.formFields = response.data.formFields;
          console.log(response.data.formFields);
          this.$store.commit("addProcessID", response.data.processInstanceId);
          this.taskId = response.data.taskId;
          console.log(this.taskId);
          console.log(response.data.processInstanceId);
        
        })
        .catch((error) => {
          console.log(error);
        });
    },
    add(){
        if (this.$refs.form.validate()) {
          let formSubmissionDto = new Array();
          this.formFields.forEach((formField) => {
            formSubmissionDto.push({
              id: formField.id,
              fieldValue: formField.fieldValue,
            });
          });
          var writer = { id: 'writer', fieldValue: this.$store.state.user.username }
          formSubmissionDto.push(writer)
          console.log(formSubmissionDto);
            Axios.post(
            "http://localhost:8080/subminForm/" + this.taskId + '/' + 'form',
            formSubmissionDto
          )
            .then((response) => {
              this.close();
              console.log(response);
              
            })
            .catch((error) => {
              console.log(error);
            });
        
      } else {
        console.log("nije validno");
        }
    }
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