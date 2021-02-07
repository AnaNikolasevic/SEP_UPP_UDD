<template>
  <div>
    <v-btn text color="primary" @click="confirm()">Confirm Subscription</v-btn>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Home",
  data() {},

  methods: {
    confirm() {
      const queryString = window.location.search;
      const urlParams = new URLSearchParams(queryString);
      const token = urlParams.get("token");

      axios
        .post("http://localhost:8081/executeSubscription/" + token + "/1")
        .then((response) => {
          console.log(response);
          window.open("http://localhost:8083/subscriptionSuccess");
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {},
};
</script>