<template>
    <div class="row list">
        <div class="col-md-6">
    <!--<div>-->
        <!--<div>-->
            <h4>Project List</h4>
            <ul>
                <li v-for="(project, index) in projects" :key="index">
                    <router-link :to="{
                            name: 'project',
                            params: { project: project, id: project.id }
                        }">
                        {{project.name}}
                    </router-link>
                </li>
            </ul>
        </div>
        <div class="column">
            <app-project @refreshData="refreshList"></app-project>
        </div>
    </div>
</template>

<script>
    import http from "../../http-common";

    import Project from '../Main/Project';
    import Menubar from '../Header/Menubar'

    export default {
        components: {
            'app-project': Project
        },
        name: "main",
        data() {
            return {
                customers: []
            };
        },
        methods: {
            /* eslint-disable no-console */
            retrieveCustomers() {
                http
                    .get("/customers")
                    .then(response => {
                        this.customers = response.data; // JSON are parsed automatically.
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            refreshList() {
                this.retrieveCustomers();
            }
            /* eslint-enable no-console */
        },
        mounted() {
            this.retrieveCustomers();
        }
    };
</script>

<style>
    .list{
        text-align: left;
        /*max-width: 450px;*/
        margin: auto;
        width: 100%;
    }
    .col-md-6{
        float: right;
        text-align: right;
        width: 30%;
        padding: 10px;
        height: 300px; /* Should be removed. Only for demonstration */
        background-color:#c9302c;
    }
    /* Create two equal columns that floats next to each other */
    .column {
        float: left;
        width: 50%;
        padding: 10px;
        height: 300px; /* Should be removed. Only for demonstration */
        background-color:#aaa;
    }

    /* Clear floats after the columns */
    .row:after {
        content: "";
        display: table;
        clear: both;
    }
</style>
