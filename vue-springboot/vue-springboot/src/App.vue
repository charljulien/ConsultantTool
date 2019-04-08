<template>
    <div id="app" class="container-fluid">
        <div class="site-info">
            <h1>grokonez</h1>
            <h3>Vue SpringBoot example</h3>
        </div>
        <br/>
        <router-view/>
        <div v-if="login">
            <app-main></app-main>
        </div>
    </div>
</template>
<!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">-->
<!--<style src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"></style>-->


<script>
    // // Imports
    import Main from './components/Main/Main';
    import Project from './components/Main/Project';
    // import Login from './components/user/Login';
    // import Register from './components/user/Register';
    //
    // import Header from './components/ninjanet/Header.vue';
    // import Footer from './components/ninjanet/Footer.vue';
    // import Ninjas from './components/ninjanet/Ninjas.vue';
    export default {
        components: {
            'app-main': Main,
            'app-project': Project
        },
        name: "app",
        data(){
            return{
                login: true,
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
