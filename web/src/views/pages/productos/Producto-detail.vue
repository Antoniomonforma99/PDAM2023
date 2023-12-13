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

    function gotoEditProducto(idProducto) {
        router.push(
            { name : 'producto-edit',
            params : {idProducto}}
        )
    }

    onMounted(() => {
        idProducto.value = route.params.idProducto;
        getProductoById(idProducto.value);
       
    });

    async function deleteProducto(id) {
        await productoService.deleteProducto(id);

        toast.add({
                        severity : 'success',
                        summary: 'Producto eliminado',
                        life : 1500
                    })

        setTimeout(() => {
            router.push(
            {
                name : 'productos-list'
            }
        )
        }, 1500)


    }

    function muestra(){
        console.log(producto.value.ingredientes)
    }

</script>

<template>
    <Toast position="top-center" />

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
                        <button @click="deleteProducto(producto.id)" class="col-6">ELIMINAR PRODUCTO</button>
                        <button @click="gotoEditProducto(producto.id)" class="pointer col-6">EDITAR PRODUCTO</button>
                    </div>
                </div>
                    

            </div>
        </div>
    
</template>

<style lang="scss" scoped>
    .pointer {
        cursor : pointer;
    }</style>