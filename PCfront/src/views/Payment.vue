<template>
  <v-row justify="center">
  </v-row>
</template>

<script>
import axios from "axios";

export default {

  data: () => ({
    LoginDialog: false,
    password: "",
    params: "",
    token: "",
    milica: "",
    list: [],
    list2: [],
    paymentId: "",
    PayerID: "",
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
  mounted() {
    console.log(this.$route);
    this.list = this.$route.fullPath.split("=");
    this.list2 = this.list[1].split("&");
    this.paymentId = this.list2[0];
    this.PayerID = this.list[3];

    console.log(this.paymentId);
    console.log(this.PayerID);

    axios
      .get(
        "http://localhost:8081/success/" + this.paymentId + "/" + this.PayerID
      )
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
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