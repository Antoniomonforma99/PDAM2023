<script setup>
    import ProductoService from '../../../services/ProductoService';
    import CategoriaService from '../../../services/CategoriaService';
    import { ref, onBeforeMount, onMounted, watch } from 'vue';
    import { useToast } from "primevue/usetoast";
    import router from '../../../routes';
    import { useRoute } from 'vue-router';

    const route = useRoute()
    const toast = useToast();

    const idProducto = ref('');
    const producto = ref({});
    const categorias = ref([]);

    const nombre = ref('');
    const precio = ref('');
    const description = ref('');
    const ingredientes = ref('');
    const idCategoriaSelected = ref ('');

    const productoService = new ProductoService();
    const categoriaService = new CategoriaService();

    async function getProductoById(idProducto) {
        await productoService.getProductoById(idProducto)
            .then((resp) => {
                
                producto.value = resp;                              
            });          
    }

    async function getCategorias(){
    await categoriaService.getAll().then((resp) => {
        categorias.value = resp;
    })
}

    onMounted(async () => {
        idProducto.value = route.params.idProducto;
        getCategorias();

        await getProductoById(idProducto.value);
        nombre.value = producto.value.nombre;
        precio.value = producto.value.precio;
        description.value = producto.value.descripcion;
        ingredientes.value = producto.value.ingredientes;
        idCategoriaSelected.value = producto.value.categoria;
       
    });

    async function editarProducto(){
        let productoRequestDTO = {
            nombre : nombre.value,
            precio : precio.value,
            descripcion : description.value,
            ingredientes : ingredientes.value.split(","),
            idCategoria : idCategoriaSelected.value
        };

        console.log("let" +JSON.stringify(productoRequestDTO))


        await productoService.editProducto(productoRequestDTO, idProducto.value)
            .then((resp) => {
                if (resp === 1) {
                    toast.add({
                    severity : 'success',
                    summary: 'Producto editado con éxito',
                    life : 3000
                })
            } else {
                toast.add({
                    severity : 'error',
                    summary: 'Ha ocurrido algún problema',
                    life : 3000
                })
            }
            })

            

    
    }

</script>

<template>
    <Toast position="top-center" />

    <div class="grid">
        <div class="col-12">
            <div class="card">
                <div style="display: flex; align-items: baseline; justify-content: space-between;">
                    <h4 class="ml-3">Editar producto</h4>
                    <div style="display: flex;" class="ml-5">
                        <Button @click="editarProducto()" label="Editar producto" outlined class="mr-5 mb-5" />
                    </div>
                </div>
                <div class="p-fluid formgrid grid">
                    <div class="field col-12 md:col-6">
                        <label for="nombre">Nombre</label>
                        <InputText id="nombre" v-model="nombre" type="text" />
                    </div>
                    <div class="field col-12 md:col-6">
                        <label for="precio">Precio</label>
                        <InputText id="precio" v-model="precio" type="double" />
                    </div>
                    <div class="field col-12">
                        <label for="description">Descripción</label>
                        <Textarea id="description" v-model="description" rows="4" />
                    </div>
                    <div class="field col-12 md:col-6">
                        <label for="ingredientes">Ingredientes separados por coma</label>
                        <InputText id="ingredientes" v-model="ingredientes" type="text" />
                    </div>
                    <div class="field col-12 md:col-6">
                        <label for="state">Categoría</label>
                        <Dropdown id="state" v-model="idCategoriaSelected" :options="categorias" :optionLabel="categoria => categoria.nombre" :optionValue="categoria => categoria.id" placeholder="Elija una"></Dropdown>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<style lang="scss" scoped>
</style>