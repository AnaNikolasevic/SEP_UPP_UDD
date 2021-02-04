import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import ActivationLink from '../views/ActivationLink.vue'
import Cart from '../components/Cart.vue'
import MandatoryBooks from '../components/MandatoryBooks.vue'
import BookPreview from "../views/editor/BookPreview.vue";
import BookPlagiarism from "../views/editor/BookPlagiarism.vue"
import ChooseToSendToBetaReaders from "../components/editor/ChooseToSendToBetaReaders.vue"
import ChooseBetaReaders from "../components/editor/ChooseBetaReaders.vue"

import UploadBook from "../components/homePage/WriterComponents/UploadBook.vue";
import UploadMandatory from "../components/UploadMandatory.vue"
import CheckMistakes from "../views/lector/CheckMistakes.vue"
import UploadEdidetBook from "../components/homePage/WriterComponents/UploadEditedBook.vue"
import FinalCheck from "../views/editor/FinalCheck.vue"
import FinalEdit from "../components/homePage/WriterComponents/FinalEdit.vue"
import FinalApprovement from "../views/editor/FinalApprovement.vue"

Vue.use(VueRouter)

const routes = [{
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
    {
        path: '/uplaodMandatory',
        name: 'UplaodMandatory',
        component: UploadMandatory
    },
    {
        path: '/mandatoryBooks',
        name: 'MandatoryBooks',
        component: MandatoryBooks
    },
    {
        path: "/bookPreview",
        name: "BookPreview",
        component: BookPreview,
    },
    {
        path: "/bookPlagiarism",
        name: "BookPlagiarism",
        component: BookPlagiarism,
    },
    {
        path: "/uploadBook",
        name: "uploadBook",
        component: UploadBook,
    },
    {
        path: "/chooseToSendToBetaReaders",
        name: "ChooseToSendToBetaReaders",
        component: ChooseToSendToBetaReaders,
    },
    {
        path: "/chooseBetaReaders",
        name: "ChooseBetaReaders",
        component: ChooseBetaReaders,
    },
    {
        path: "/checkMistakes",
        name: "CheckMistakes",
        component: CheckMistakes,
    },
    {
        path: "/uploadEdidetBook",
        name: "UploadEdidetBook",
        component: UploadEdidetBook,
    },
    {
        path: "/finalChceck",
        name: "FinalCheck",
        component: FinalCheck,
    },
    {
        path: "/finalEdit",
        name: "FinalEdit",
        component: FinalEdit,
    },
    {
        path: "/finalApprovement",
        name: "FinalApprovement",
        component: FinalApprovement
    }

]

const router = new VueRouter({
    mode: 'history',
    //base: process.env.BASE_URL,
    routes
})

export default router