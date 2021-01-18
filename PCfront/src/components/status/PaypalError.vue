<template>
  <div>
    <h1>Your payment was canceled or expired!</h1>
    <v-btn text color="primary" @click="redirectToLU()">Return to LU</v-btn>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data: () => ({
    queryString: "",
    urlParams: [],
    orderId: "19",
    status: "canceled",
  }),
  methods: {
    getUrlParametres() {
      this.queryString = window.location.search;
      this.urlParams = new URLSearchParams(this.queryString);
      this.orderId = this.urlParams.get("orderId");
    },
    changeOrderRequestStatus() {
      this.getUrlParametres();

      axios
        .put(
          "http://localhost:8082/orderRequest/edit/" +
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
    cancelPaypal() {
      this.getUrlParametres();

      axios
        .put("http://localhost:8081/payment/" + this.orderId)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.cancelPaypal();
    this.changeOrderRequestStatus();
  },
};
</script>