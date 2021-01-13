<template>
  <v-layout row wrap>
    <div v-if="emptyBasket" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
      <v-flex>
        <v-card hover elevation="2" class="text-center ma-6">
          <div class="detailsBorderColor">
            <v-card-text style="align: center" class="headline">
              <h2>Your basket is empty</h2>
              <br />
              <router-link to="/">Find the right book</router-link>
            </v-card-text>
          </div>
        </v-card>
      </v-flex>
    </div>
    <v-snackbar v-model="snackbarSuccess" :timeout="4000" top color="success">
      <span>Book request has been sent!</span>
      <v-btn text @click="snackbarSuccess = false">Close</v-btn>
    </v-snackbar>
    <div v-if="!emptyBasket" class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
      <v-flex v-for="book in books" :key="book.title">
        <v-card hover elevation="2" class="text-center ma-6">
          <div class="cardBorderColor">
            <v-card-title class="headline"
              >Title: "{{ book.title }}"</v-card-title
            >
            <v-card-text>author: {{ book.author }}</v-card-text>
            <v-card-text>price: {{ book.price }} USD</v-card-text>
            <v-card-actions></v-card-actions>
          </div>
        </v-card>
      </v-flex>
    </div>
    <div v-if="!emptyBasket" class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
      <v-card hover elevation="2" class="text-center ma-6">
        <div class="cardBorderColor">
          <v-card-title class="primary--text font-weight-bold headline"
            >Summary</v-card-title
          >
          <v-card-text aria-disabled v-model="orderRequest.price"
            >Total price: {{ orderRequest.price }}</v-card-text
          >
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-combobox
              v-model="orderRequest.currency"
              :items="currencies"
              label="Currency"
              outlined
              dense
              @change="changeValue(orderRequest.currency)"
            ></v-combobox>
          </v-card-actions>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn @click="sendRequest()" class="primary">Order</v-btn>
          </v-card-actions>
        </div>
      </v-card>
    </div>
  </v-layout>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      emptyBasket: false,
      snackbarSuccess: false,
      agents: [],
      checkList: [],
      bundleOrder: [],
      //singleOrders: [],
      books: [
        {
          title: "Real Life Paperback",
          author: "Brandon Taylor",
          price: 25,
        },
        {
          title: "The Buried Giant",
          author: "Kazuo Ishiguro",
          price: 20,
        },
      ],
      orderRequest: {
        price: "",
        currency: "USD",
        sellerId: "1",
      },
      totalPrice: 0,
      currencies: ["USD", "EUR", "HUF"],
    };
  },
  methods: {
    createOrderFromAdd() {},
    getAgents() {},
    orderCars() {},
    changeValue(currency) {
      if (currency == "EUR")
        this.orderRequest.price = (this.totalPrice * 0.82).toFixed(2);
      else if (currency == "USD") this.orderRequest.price = this.totalPrice;
      else if (currency == "HUF")
        this.orderRequest.price = (this.totalPrice * 296).toFixed(2);
    },
    sendRequest() {
      axios
        .post("http://localhost:8082/orderRequest", this.orderRequest)
        .then((response) => {
          console.log(response.data);
          /*this.snackbarSuccess = true;
          this.$store.commit("deleteAll");
          this.emptyBasket = true;*/
          window.open("http://localhost:8083/?id=" + response.data.id);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    checkBasket() {
      /*console.log(this.$store.state.carsInCart.length);
      if (this.$store.state.carsInCart.length == 0) {
        this.emptyBasket = true;
      }*/
    },
  },
  mounted() {
    this.books.forEach((element) => {
      this.totalPrice += element.price;
    });
    this.orderRequest.price = this.totalPrice;
  },
  computed: {},
};
</script>

<style scoped>
.cardBorderColor {
  border-left: 1px solid #ff8a65;
  border-top: 1px solid #ff8a65;
  border-right: 1px solid #ff8a65;
  border-bottom: 1px solid #ff8a65;
}
.detailsBorderColor {
  border-left: 1.5px solid #ff8a65;
  border-top: 1.5px solid #ff8a65;
  border-right: 1.5px solid #ff8a65;
  border-bottom: 1.5px solid #ff8a65;
}
</style>
