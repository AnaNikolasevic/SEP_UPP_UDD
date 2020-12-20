import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import ActivationLink from '../views/ActivationLink.vue'
import Cart from '../components/Cart.vue'



Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'HomePage',
    component: HomePage
  },
    {
    path: "/activationForm/:token",
    name: "ActivationLink",
    component: ActivationLink,
  },
  {
    path: '/cart',
    name: 'Cart',
    component: Cart
  },

]

const router = new VueRouter({
  mode: 'history',
  //base: process.env.BASE_URL,
  routes
})

export default router
