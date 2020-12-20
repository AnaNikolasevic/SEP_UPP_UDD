<template>
  <v-row justify="center">
    <v-dialog v-model="LoginDialog" max-width="600px">
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
                  label="Email*"
                  color="primary"
                  v-model="email"
                  required
                  :rules="emailRules"
                ></v-text-field>

                <v-text-field
                  color="primary"
                  label="Password*"
                  v-model="password"
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
    password: "",
    token: "",
    milica: "",
    passwordRules: [(v) => !!v || "Password is required"],
    email: "",
    emailRules: [
      (v) => !!v || "E-mail is required",
      (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
    ],
  }),
  methods: {
    login() {
      if (this.$refs.form.validate()) {
        console.log(this.password + " " + this.email);

        axios
          .get("http://localhost:8082/token")
          .then((response) => {
            this.token = response;
            console.log(response);
          })
          .catch((error) => {
            console.log(error);
          });

        if (this.email == "mc@gmail.com") {
          this.$emit("loggedIn");
        } else {
          this.$emit("notLoggedIn");
        }

        //this.close();
      } else {
        console.log("nije validno");
      }
    },
    close() {
      console.log(this.token.data);
      axios
        .post(
          "http://localhost:8081/pay",
          { action: "dashboard" },
          {
            headers: {
              Authorization: this.token.data,
            },
          }
        )
        .then((response) => {
          console.log(response);
          window.location.href = response.data;
        })
        .catch((error) => {
          console.log(error);
        });

      this.LoginDialog = false;
      this.$refs.form.reset();
    },
  },
};
</script>

<style scoped>
.cardBorderColor {
  border-left: 1px solid #26a69a;
  border-top: 1px solid #26a69a;
  border-right: 1px solid #26a69a;
  border-bottom: 1px solid #26a69a;
}
.detailsBorderColor {
  border-left: 2px solid #26a69a;
  border-top: 2px solid #26a69a;
  border-right: 2px solid #26a69a;
  border-bottom: 2px solid #26a69a;
}
</style>