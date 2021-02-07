<template>
  <div>
    <h1>Welcome!</h1>
    <v-combobox
      v-model="choosenType"
      :items="paymentTypes"
      item-text="name"
      label="Choose payment type"
      outlined
      dense
      @change="setOrder()"
    ></v-combobox>
    <v-combobox
      v-model="chosenSubscriptionType"
      :items="subscriptionTypes"
      item-text="name"
      label="Choose subscription type"
      outlined
      dense
    ></v-combobox>
    <v-btn text color="primary" @click="proceed()">Proceed</v-btn>
    <v-btn text color="primary" @click="subscribe()">Subscribe</v-btn>
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
      if (this.choosenType.name == "paypal") {
        axios
          .post(
            "http://localhost:8081/pay",
            { action: "dashboard" },
            {
              headers: {
                Authorization: this.$store.state.token,
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
      } else if (this.choosenType.name == "bitcoin") {
        axios
          .post(
            "http://localhost:8089/pay",
            { action: "dashboard" },
            {
              headers: {
                Authorization: this.$store.state.token,
              },
            }
          )
          .then((response) => {
            console.log(response);
            window.open(response.data.body);
          })
          .catch((error) => {
            console.log(error);
          });
      } else if (this.choosenType.name == "card") {
        console.log("ovo je token u proceed metodi");
        console.log(this.$store.state.token);
        //DODAT HTTPS 
        axios
          .post(
            "https://localhost:8090/pay",
            { action: "dashboard" },
            {
              headers: {
                Authorization: this.$store.state.token,
              },
            }
          )
          .then((response) => {
            console.log(response);
            window.open(response.data);
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    subscribe() {
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
    setOrder() {
      console.log("prosledjenjo");
      console.log(this.choosenType.name);
      const queryString = window.location.search;
      const urlParams = new URLSearchParams(queryString);
      const id = urlParams.get("id");
      axios
        .put(
          "http://localhost:8082/orderRequest/" +
            id +
            "/" +
            this.choosenType.name
        )
        .then((response) => {
          console.log("serOrder response");
          console.log(response.data);
          this.getToken(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getToken(order) {
      axios
        .post("http://localhost:8082/token", order)
        .then((response) => {
          this.$store.commit("addToken", response.data);
          console.log("token u soridzu");
          console.log(this.$store.state.token);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.$store.commit("removeToken");

    //dobavljanje liste nacina placanje, nekog selera!
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const idSeller = urlParams.get("idSeller");
    axios
      .get("http://localhost:8082/seller/paymentTypes/" + idSeller)
      .then((response) => {
        console.log(response.data);
        this.paymentTypes = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>