<template>
  <v-row justify="center">
    <v-dialog
      v-model="LoginDialog"
      max-width="600px"
      v-if="this.$store.state.user.role == 'none'"
    >
      <template v-slot:activator="{ on }">
        <v-btn class="mx-1" text color="primary" v-on="on">
          <span>Login</span>
          <LoginIcon right>lock_open</LoginIcon>
        </v-btn>
      </template>
      <div class="detailsBorderColor">
        <v-card>
          <v-card-title>
            <span class="primary--text font-italic headline" primary-title
              >Login</span
            >
            <v-spacer></v-spacer>
            <v-btn icon color="primary" @click="LoginDialog = false">
              <CloseIcon></CloseIcon>
            </v-btn>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-form ref="form">
                <v-text-field
                  label="Username*"
                  color="primary"
                  v-model="user.username"
                  required
                ></v-text-field>

                <v-text-field
                  color="primary"
                  label="Password*"
                  v-model="user.password"
                  type="password"
                  required
                  :rules="passwordRules"
                ></v-text-field>
              </v-form>
            </v-container>
          </v-card-text>
          <v-card-actions class="pr-10 pb-10">
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="close">Cancel</v-btn>
            <v-btn color="primary" @click="login">Login</v-btn>
          </v-card-actions>
        </v-card>
      </div>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";
import LoginIcon from "vue-material-design-icons/Login.vue";
import CloseIcon from "vue-material-design-icons/CloseCircle.vue";
export default {
  components: {
    LoginIcon,
    CloseIcon,
  },
  data: () => ({
    LoginDialog: false,
    passwordRules: [(v) => !!v || "Password is required"],
    user: {
      username: "",
      password: "",
    },
    snackbarSuccess: false,
    snackbarSuccessText: "",
    snackbarDanger: false,
    snackbarDangerText: "",
  }),
  methods: {
    login() {
      axios
        .post("http://localhost:8080/login", this.user)
        .then((response) => {
          this.$store.commit("login", response.data);
          console.log("ulogovala si se:");
          console.log("ROLE: " + this.$store.state.user.role);
          console.log(this.$store.state.user);
          this.$emit("loggedIn");
          this.close();
        })
        .catch((error) => {
          console.log(this.user);
          this.$emit("notLoggedIn");
          console.log(error);
        });
    },
    close() {
      this.LoginDialog = false;
      this.$refs.form.reset();
    },
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