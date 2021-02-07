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
        },
      ],
      choosenType: "",
      token: "",
      proba: {},
      orderRequest: {
        currency: "",
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
    const idSeller = urlParams.get('idSeller');
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