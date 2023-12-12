<script setup>
    import router from '../../routes';

    function goToNewProducto() {
            router.push(
                {
                    name : 'producto-new'
                }
            )
    }
    function goToNewCategoria() {
        router.push(
            {
                name : 'categoria-new'
            }
        )
    }
    function goToNewUser() {
        router.push(
            {
                name : 'personal-new'
            }
        )
    }
</script>

<template>
    <div class="grid">
            <div style="width: 100vw;" class="card container">
                <div @click="goToNewProducto()" style="height: 200px; align-items: center;" class="col-2 pointer card mb-0">
                    <h5>NUEVO PRODUCTO</h5>
                </div>
                <div @click="goToNewCategoria()" style="height: 200px; " class="col-2 pointer card mb-0">
                    <h5>NUEVA CATEGORÍA</h5>
                </div>
                <div @click="goToNewMenu()" style="height: 200px; " class="col-2 pointer card mb-0">
                    <h5>NUEVO MENÚ</h5>
                </div>
                <div @click="goToNewUser()" style="height: 200px; " class="col-2 pointer card mb-0">
                    <h5>NUEVO USUARIO</h5>
                </div>
            </div>
    </div>

    
</template>

<style lang="scss" scoped>
.container {
    display: flex;
    justify-content: space-between;
    
}
    .pointer {
        cursor : pointer;
    }
</style>