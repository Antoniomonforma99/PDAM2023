<script setup>
import { ref, onBeforeMount, onMounted } from 'vue';
import { useToast } from "primevue/usetoast";
import ProductoService from '../../../services/ProductoService';
import CategoriaService from '../../../services/CategoriaService';
import router from '../../../routes';

const productoService = new ProductoService;
const categoriaService = new CategoriaService;

const categorias = ref([]);
const nombreCategorias = ref([]);

const toast = useToast();

async function getCategorias(){
    await categoriaService.getAll().then((resp) => {
        categorias.value = resp;
    })
}

const nombre = ref('');
const precio = ref(0);
const description = ref('');
const ingredientes = ref('');
const idCategoriaSelected = ref ('');
const img = ref(null);



async function createProducto(){
    let productoRequestDTO = {
        nombre : nombre.value,
        precio : precio.value,
        descripcion : description.value,
        ingredientes : ingredientes.value.split(","),
        idCategoria : idCategoriaSelected.value
    };

    console.log(productoRequestDTO);
    console.log("imagen" +img.value);

    await productoService.createProducto(productoRequestDTO, img.value)
        .then((resp) => {
            if (resp === 1) {
                toast.add({
                severity : 'success',
                summary: 'Producto creado con éxito',
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

        router.push('/productos')

    
}

function onUpload(event){
    if (!event) {
        console.error("El evento es indefinido o nulo");
    
    }
    const fileList = event.target?.files;
    if (!fileList || fileList.length === 0) {
        console.error("No se seleccionó ningún archivo");
        
    }
    
    console.log("uip" +event.target)
    const file = event.target.files[0];
    img.value = file;

}

onMounted(() => {
    getCategorias();   
}) 

</script>

<template>
    <div></div>
    <Toast position="top-center" />

    <div class="grid">
        <div class="col-12">
            <div class="card">
                <div style="display: flex; align-items: baseline; justify-content: space-between;">
                    <h4 class="ml-3">Nuevo producto</h4>
                    <div style="display: flex;" class="ml-5">
                        <Button @click="createProducto()" label="Crear producto" outlined class="mr-5 mb-5" />
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
                    <div class="field col-12 md:col-12">
                        <label for="imagen">Imágenes</label>
                        
                        <input type="file" @change="onUpload" accept="image/*" />
                    </div>
                    

                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>

</style>