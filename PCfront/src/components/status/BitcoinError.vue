<template>
  <div>
    <h1>Your payment was canceled or expired</h1>
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
      status: "canceled",
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
          "http://localhost:8082/orderRequest/" +
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
    bitcoinError() {
      this.getUrlParametres();

      axios
        .put("http://localhost:8089/cancel/" + this.paymentOrderId)
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.bitcoinError();
    this.changeOrderRequestStatus();
  },
};
</script>