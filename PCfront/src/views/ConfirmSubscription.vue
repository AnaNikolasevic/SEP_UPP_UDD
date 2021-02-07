<template>
  <div>
    <h1>Confirm subscription!</h1>

    <v-btn text color="primary" @click="confirm()">Confirm</v-btn>
    <v-btn text color="primary" @click="cancel()">Cancel</v-btn>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Home",
  data() {
    return {
      paymentTypes: [
        {
          name: "",
        },
      ],
      choosenType: "",
      token: "",
      proba: {},
      idLU: "",
      seller: {},
      bitcoin: {},
    };
  },
  methods: {
    confirm() {
      axios
        .post(
          "http://localhost:8081/executeSubscription/EC-27396731WY103323W/1"
        )
        .then((response) => {
          console.log(response);
          this.addPaymentInfo();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {},
};
</script>