<script setup>
    import ProductoService from '../../../services/ProductoService';
    import { ref, onBeforeMount, onMounted, watch } from 'vue';
    import { useToast } from "primevue/usetoast";
    import router from '../../../routes';
    import { useRoute } from 'vue-router';

    const route = useRoute()
    const toast = useToast();

    const idProducto = ref('');
    const producto = ref({});


    const productoService = new ProductoService();

    async function getProductoById(idProducto) {
        await productoService.getProductoById(idProducto)
            .then((resp) => {
                
                producto.value = resp;  

                console.log(typeof(resp))
                console.log(typeof(producto.value));

                             
            });
            
            console.log(typeof(producto.value));
            
    }

    onMounted(() => {
        idProducto.value = route.params.idProducto;
        getProductoById(idProducto.value);
       
    });

    function muestra(){
        console.log(producto.value.ingredientes)
    }

</script>

<template>

        <div class="col-12">
            <div class="card">
                <h2 style="text-align: center;">{{ producto.nombre }}</h2>
                <div class="grid">
                    <div class="col-6">
                        <div class="flex justify-content-center">
                            <Image :src="'http://localhost:8080/download/' +producto.imgUrl" alt="Image" width="250" preview />
                        </div>
                        
                    </div>

                    <div class="col-6">
                        <div class="mt-5 justify-content-center">
                            <h4> {{ producto.descripcion }}</h4>
                            <br>
                            <h5> CATEGORIA {{ producto.categoria }}</h5>
                            <h5> INGREDIENTES</h5>
                            <p>{{ producto.ingredientes }}</p>
                            <!-- <p :v-for="(ingrediente, index) in producto.ingredientes" :key="index">{{ ingrediente }}</p> -->
                            <br>
                            <h5>PRECIO:  {{ producto.precio }}€</h5>
                            <br>
                            <Rating :modelValue="producto.valoracionMedia" :readonly="true" :cancel="false"></Rating>
                        </div>
                    </div>
                    <div class="col-12 container">
                        <button class="col-6">ELIMINAR PRODUCTO</button>
                        <button class="col-6">EDITAR PRODUCTO</button>
                    </div>
                </div>
                    

            </div>
        </div>
    
</template>

<style lang="scss" scoped></style>