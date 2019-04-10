<template>
    <div id="app" class="container-fluid">
        <div class="site-info">
            <h1>Project Consultant tool</h1>
            <h3>Vue SpringBoot example</h3>
        </div>

        <div v-if="!login">
            <nav>
                <router-link class="btn btn-primary" to= "/login">Login</router-link>
                <router-link class="btn btn-primary" to="/register">Register</router-link>
            </nav>
        </div>
        <div v-else>
            <nav>
                <h4>Welcome {{ username }} </h4>
                <button class="btn btn-primary" v-on:click="logout()">Logout</button>
                <router-link class="btn btn-primary" to="/edit">Edit</router-link>
            </nav>
        </div>




        <br/>
        <router-view v-bind:username="username" v-bind:login="login" @userSession="initUsername" @isUserActive="initSession"></router-view>
        <div v-if="login">
            <!--<router-view v-bind:username="username"></router-view>-->
            <!--<app-project-list v-bind:username="username"></app-project-list>-->
        </div>
    </div>
</template>
<!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">-->
<!--<style src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"></style>-->


<script>
    // // Imports
    import Projectlist from './components/Main/Project-list';
    import Project from './components/Main/Project';
    import Menubar from "./components/Header/Menubar";
    // import Register from './components/user/Register';
    //
    // import Header from './components/ninjanet/Header.vue';
    // import Footer from './components/ninjanet/Footer.vue';
    // import Ninjas from './components/ninjanet/Ninjas.vue';
    export default {
        components: {
            'app-menubar': Menubar,
            'app-project-list': Projectlist,
            'app-project': Project
        },
        name: "app",
        data(){
            return{
                login: false,
                username: "",
                ninjas: [
                    {name: 'Ryu', speciality: 'Vue Components', show: false},
                    {name: 'Crystal', speciality: 'HTML Wizardry', show: false},
                    {name: 'Hitoshi', speciality: 'Click Events', show: false},
                    {name: 'Tango', speciality: 'Conditionals', show: false},
                    {name: 'Kami', speciality: 'Webpack', show: false},
                    {name: 'Yoshi', speciality: 'Data Diggin', show: false}
                ],
                title: 'Vue Wizards'
            }
        },
        methods: {
            updateTitle: function(updatedTitle){
                this.title = updatedTitle;
            },
            initUsername: function (value) {
                this.username = value;
            },
            initSession: function(value) {
                this.login = value;
            },

            logout(){
                this.login = false;
                this.username = "";
            }
        }
    };
</script>

<style>
    .site-info {
        color: blue;
        margin-bottom: 20px;
    }

    .btn-primary {
        margin-right: 5px;
    }

    .container-fluid {
        text-align: center;
    }
</style>

<style lang="scss">
    /*@import '../node_modules/bootstrap/scss/bootstrap.scss';*/
    @import 'assets/css/bootstrap.css';
</style>
