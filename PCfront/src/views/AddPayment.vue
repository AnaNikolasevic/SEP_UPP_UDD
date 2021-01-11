<template>
  <div>
    <h1>Welcome!</h1>
    <v-combobox
        v-model="choosenType"
        :items="paymentTypes"
        item-text="name"
        label="Choose payment type to add"
        outlined
        dense
    ></v-combobox>
    <div v-if="choosenType.name=='paypal'">
        <v-text-field label="Paypal client id" v-model="seller.paypalClientId"></v-text-field>
        <v-text-field label="Paypal secret" v-model="seller.paypalSecret"></v-text-field>
        <v-text-field label="Paypal email" v-model="seller.email"></v-text-field>

    </div>
    <div v-if="choosenType.name=='bitcoin'">
        <v-text-field label="Coingate token" v-model="seller.coingateToken"></v-text-field>
        <v-text-field label="Coingate email" v-model="seller.email"></v-text-field>
    </div>
    <v-btn text color="primary" @click="add()">Add</v-btn>
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
        }
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
    add(){
        axios
        .put(
          "http://localhost:8082/seller/paymentTypes/" + this.idLU + "/" + this.choosenType.id)
        .then((response) => {
          console.log(response);
          this.addPaymentInfo();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addPaymentInfo(){
        if (this.choosenType.name == "paypal")
            this.addPayPal();
        else if (this.choosenType.name == "bitcoin")
            this.addBitcoin();
        else if (this.choosenType.name == "card")
            this.addCard();
    },
    addPayPal(){
        axios
        .post(
          "http://localhost:8081/seller", this.seller)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addBitcoin(){
      axios
        .post(
          "http://localhost:8089/seller", this.seller)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addCard(){},
  },
  mounted() {
    axios
      .get("http://localhost:8082/paymentType/paymentTypes")
      .then(response => {
        console.log(response.data);
        this.paymentTypes = response.data;
      })
      .catch(error => {
        console.log(error);
      });

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    this.idLU = urlParams.get('idLU');
  }
};
</script>