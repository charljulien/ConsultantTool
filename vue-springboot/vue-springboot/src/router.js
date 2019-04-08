import Vue from "vue";
import Router from "vue-router";
import Menubar from "./components/Header/Menubar";
import Login from "./components/Header/Login";
import Register from "./components/Header/Registration";
import Main from "./components/Main/Main";
import Project from "./components/Main/Project";


Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            redirect: {
                name: "menubar"
            }
        },
        {
            path: "/menubar",
            name: "menubar",
            component: Menubar,
            children: [
                {
                    path: "login",
                    name: "login",
                    component: Login,
                },
                {
                    path: "register",
                    name: "register",
                    component: Register,
                }
            ]
        },
        {
            path: "/homepage",
            name: "Main",
            component: Main
        },
        {
            path: "/Project",
            name: "Project",
            component: Project
        }
    ]
});