<template>
    <div class="list row">
        <div class="col-md-6">
            <h4>Customers List</h4>
            <ul>
                <li v-for="(customer, index) in customers" :key="index">
                    <router-link :to="{
                            name: 'customer-details',
                            params: { customer: customer, id: customer.id }
                        }">
                        {{customer.name}}
                    </router-link>
                </li>
            </ul>
        </div>
        <div class="col-md-6">
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
    .list {
        text-align: left;
        max-width: 450px;
        margin: auto;
    }
</style>
