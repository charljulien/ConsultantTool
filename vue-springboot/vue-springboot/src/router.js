import Vue from "vue";
import Router from "vue-router";
import login from "./components/login.vue";
import home from  "./components/home.vue"


Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/login",
            name: "login",
            component: login
        },
        {
            path: "/home",
            name: "home",
            component: home
        }
    ]
});