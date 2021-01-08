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
      paymentTypes: [
        {
          name: "",
        }
      ],
      choosenType: "",
      token: "",
      proba: {},
    };
  },
  methods: {
    proceed(){
      if(this.choosenType.name == 'paypal'){
        axios
        .post(
          "http://localhost:8081/pay",
          { action: "dashboard" },
          {
            headers: {
              Authorization: this.token.data,
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
      } else if (this.choosenType.name == 'bitcoin') {
        axios
          .post(
          "http://localhost:8089/pay",
          { action: "dashboard" },
          {
            headers: {
              Authorization: this.token.data,
            },
          }
          )
          .then(response => {
            console.log(response);
          })
          .catch(error => {
            console.log(error);
          });
      }
    }
  },
  mounted() {
    axios
      .get("http://localhost:8082/seller/paymentTypes/" + 1)
      .then(response => {
        console.log(response.data);
        this.paymentTypes = response.data;
      })
      .catch(error => {
        console.log(error);
      });

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('id')

    axios
      .get("http://localhost:8080/orderRequest/" + id)
      .then(response => {
        console.log(response.data);
        var orderRequest = response.data;
        axios
          .put("http://localhost:8082/token", orderRequest)
          .then((response) => {
            this.token = response;
            console.log(response);
          })
          .catch((error) => {
            console.log(error);
          });
      })
      .catch(error => {
        console.log(error);
      });
  }
};
</script>