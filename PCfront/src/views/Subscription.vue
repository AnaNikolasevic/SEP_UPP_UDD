<template>
  <div>
    <h1>Choose your subscription plan!</h1>
    <v-combobox
      v-model="chosenSubscriptionType"
      :items="subscriptionTypes"
      item-text="name"
      label="Choose subscription plan"
      outlined
      dense
    ></v-combobox>
    <v-btn text color="primary" @click="proceed()">Proceed</v-btn>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Home",
  data() {
    return {
      token: "",
      proba: {},
      chosenSubscriptionType: "",
      subscriptionTypes: ["monthly", "yearly"],
      orderRequest: {
        currency: "",
      },
      subscriptionRequestDTO: {
        sellerId: 1,
        name: "mothlySubscription",
        description: "mothlySubscription",
        type: "FIXED",
        frequency: "MONTH",
        frequencyIntrval: "1",
        cycles: "12",
        amount: "20",
        currency: "USD",
        successURL: "http://localhost:8083/paypalSuccess/?orderId=1",
        failureUrl: "AA",
      },
      subscriptionRequestDTO1: {
        sellerId: 1,
        name: "yearlySubscription",
        description: "yearlySubscription",
        type: "FIXED",
        frequency: "YEAR",
        frequencyIntrval: "1",
        cycles: "1",
        amount: "200",
        currency: "USD",
        successURL: "http://localhost:8083/paypalSuccess/?orderId=1",
        failureUrl: "AA",
      },
    };
  },
  methods: {
    proceed() {
      console.log("uso");
      console.log(this.chosenSubscriptionType);
      if (this.chosenSubscriptionType == "monthly") {
        console.log("milica");
        axios
          .post(
            "http://localhost:8081/createSubscription",
            this.subscriptionRequestDTO
          )
          .then((response) => {
            console.log(response);
            window.location.href = response.data.paymentUrl;
          })
          .catch((error) => {
            console.log(error);
          });
      } else if (this.chosenSubscriptionType == "yearly") {
        console.log("masa");
        axios
          .post(
            "http://localhost:8081/createSubscription",
            this.subscriptionRequestDTO1
          )
          .then((response) => {
            console.log(response);
            window.location.href = response.data.paymentUrl;
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  },
  mounted() {},
};
</script>