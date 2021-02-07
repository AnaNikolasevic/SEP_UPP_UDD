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
    <div v-if="choosenType.name=='card'">
        <v-text-field label="Merchant id" v-model="seller.merchantId"></v-text-field>
        <v-text-field label="Merchant password" v-model="seller.password"></v-text-field>
        <v-text-field label="PAN" v-model="seller.pan"></v-text-field>
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
          "http://localhost:8082/seller/paymentTypes/" + this.idLU + "/" + this.choosenType.id, this.seller)
        .then((response) => {
          console.log(response);
          alert("Successfully added!")
          location.reload();
        })
        .catch((error) => {
          alert("This seller already has this payment type.");
          console.log(error.data);
        });
    },
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