<template>
  <div>
    <h1>Your payment was successfull!</h1>
    <v-btn text color="primary" @click="redirectToLU()">Return to LU</v-btn>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      paymentTypes: [
        {
          name: "",
        },
      ],
      choosenType: "",
      token: "",
      paymentOrderId: "",
      status: "paid",
      proba: {},
    };
  },
  methods: {
    getUrlParametres() {
      this.queryString = window.location.search;
      this.urlParams = new URLSearchParams(this.queryString);
      this.paymentOrderId = this.urlParams.get("orderId");
    },
    redirectToLU() {
      window.location = window.open("http://localhost:8084");
    },
    changeOrderRequestStatus() {
      this.getUrlParametres();

      axios
        .put(
          "http://localhost:8082/orderRequest/edit/" +
            this.paymentOrderId +
            "/" +
            this.status
        )
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    bitcoinSuccessfull() {
      this.getUrlParametres();

      axios
        .put("http://localhost:8089/success/" + this.paymentOrderId)
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.bitcoinSuccessfull();
    this.changeOrderRequestStatus();
  },
};
</script>