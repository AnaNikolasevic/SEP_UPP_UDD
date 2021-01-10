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
        <v-text-field label="Paypal id" v-model="paypal.id"></v-text-field>
        <v-text-field label="Paypal token" v-model="paypal.token"></v-text-field>
    </div>
    <div v-if="choosenType.name=='bitcoin'">
        <v-text-field label="Bitcoin token"></v-text-field>
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
      paypal: {},
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
          "http://localhost:8081/paypal/" + this.idLU, this.paypal)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addBitcoin(){},
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