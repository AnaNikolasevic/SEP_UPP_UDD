<template>
  <div class="card-form">
    <div class="card-form__inner">
    <!--img src="../assets/aik.jpg" style="width:50%;"-->
      <div class="card-input">
        <label for="cardNumber" class="card-input__label">
          Card Number
        </label>
        <input
          v-model="payeerFrom.pan"
          autofocus
          id="cardNumber"
          class="card-input__input"
          autocomplete="off"
          type="password"
        />
      </div>
      <div class="card-input">
        <label for="cardName" class="card-input__label">
          Card Owner
        </label>
        <input
      
          id="cardName"
          class="card-input__input"
          v-model="payeerFrom.cardHolderName"
          autocomplete="off"
        />
      </div>
      <div class="card-form__row">
        <div class="card-form__col">
          <div class="card-form__group">
            <label for="cardMonth" class="card-input__label">
              Expiration Date
            </label>
            <select
              class="card-input__input -select"
              id="cardMonth"
              v-model="expireMonth"
            >
              <option value="" disabled selected>Month</option>
              <option
                v-for="n in 12"
                :value="n < 10 ? '0' + n : n"
                :key="n"
              >
                {{ 10 > n ? "0" + n : n }}
              </option>
            </select>
            <select
              class="card-input__input -select"
              id="cardYear"
              v-model="expireYear"
            >
              <option value="" disabled selected>Year</option>
              <option
                v-for="(n, $index) in 12"
                :value="$index + currentYear"
                :key="n"
              >
                {{ $index + currentYear }}
              </option>
            </select>
          </div>
        </div>
        <div class="card-form__col -cvv">
          <div class="card-input">
            <label for="cardCvv" class="card-input__label">CVV</label>
            <input
              v-model="payeerFrom.securityCode"
              class="card-input__input"
              id="cardCvv"
              autocomplete="off"
              type="password"
            />
          </div>
        </div>
      </div>
      <button @click="submitCard()" class="card-form__button">
        Submit
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      cardNumber: "",
      expireMonth: "",
      expireYear: "",
      name: "",
      cvv: "",
      currentYear: new Date().getFullYear(),
      payeerFrom :{
        paymentId: 0,
        pan: '',
        securityCode: '',
        cardHolderName: '',
        expirationDate: ''
      }
    };
  },
  methods: {
      submitCard() {
      this.payeerFrom.expirationDate = '01-' + this.expireMonth + '-' + this.expireYear;
      this.payeerFrom.paymentId = this.$route.params.id;
      console.log(this.payeerFrom);
      //DODAR HTTPS
      axios
        .put("https://localhost:8090/pay", this.payeerFrom)
        .then((response) => {
            console.log(response.data);
            window.open(response.data);
        })
        .catch((error) => {
            console.log(error);
        });

  }
  },
  mounted() {

  },
  computed: {

  }
};
</script>


<style scoped lang="scss">
.card-container {
  margin: 30px auto 50px auto;
}

.card-form {
  max-width: 570px;
  margin: auto;
  width: 100%;

  &__inner {
    background: #fff;
    box-shadow: 0 30px 60px 0 rgba(90, 116, 148, 0.4);
    border-radius: 10px;
    padding: 20px;
  }

  &__row {
    display: flex;
    align-items: flex-start;
  }

  &__col {
    flex: auto;
    margin-right: 15px;

    &:last-child {
      margin-right: 0;
    }

    &.-cvv {
      max-width: 150px;
    }
  }

  &__group {
    display: flex;
    align-items: flex-start;
    flex-wrap: wrap;

    .card-input__input {
      flex: 1;
      margin-right: 15px;

      &:last-child {
        margin-right: 0;
      }
    }
  }

  &__button {
    width: 100%;
    height: 55px;
    background: #38a294;
    border: none;
    border-radius: 5px;
    font-size: 22px;
    font-weight: 500;
    box-shadow: 3px 10px 20px 0px rgba(35, 100, 210, 0.3);
    color: #fff;
    margin-top: 20px;
    cursor: pointer;

    &:hover {
      background: darken(#38a294, 5%);
    }
  }
}

.card-input {
  margin-bottom: 20px;
  &__label {
    margin-bottom: 5px;
    color: #1a3b5d;
    width: 100%;
    display: block;
    text-align: left;
  }
  &__input {
    width: 100%;
    height: 50px;
    border-radius: 5px;
    box-shadow: none;
    border: 1px solid #ced6e0;
    transition: all 0.3s ease-in-out;
    font-size: 18px;
    padding: 5px 15px;
    background: none;
    color: #1a3b5d;

    &:hover,
    &:focus {
      border-color: #38a294;
    }

    &:focus {
      box-shadow: 0px 10px 20px -13px rgba(32, 56, 117, 0.35);
    }
    &.-select {
      -webkit-appearance: none;
      background-image: url("/img/select.png");
      background-size: 12px;
      background-position: 90% center;
      background-repeat: no-repeat;
      padding-right: 30px;
    }
  }
}
</style>