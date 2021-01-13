<template>
  <div>
    <h1>Your payment was successfull!</h1>
    <v-btn text color="primary" @click="redirectToLU()">Return to LU</v-btn>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data: () => ({
    queryString: "",
    urlParams: [],
    paymentId: "",
    PayerID: "",
    orderId: "",
    status: "paid",
  }),
  methods: {
    getUrlParametres() {
      this.queryString = window.location.search;
      this.urlParams = new URLSearchParams(this.queryString);
      this.paymentId = this.urlParams.get("paymentId");
      this.PayerID = this.urlParams.get("PayerID");
      this.orderId = this.urlParams.get("orderId");
    },
    changeOrderRequestStatus() {
      this.getUrlParametres();

      axios
        .put(
          "http://localhost:8082/orderRequest/" +
            this.orderId +
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
    redirectToLU() {
      window.location = window.open("http://localhost:8084");
    },
    paypalSuccessfull() {
      this.getUrlParametres();

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
  },
  mounted() {
    this.paypalSuccessfull();
    this.changeOrderRequestStatus();
  },
};
</script>