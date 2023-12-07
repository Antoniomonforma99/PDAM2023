<script setup>
import { ref } from 'vue';


 
const model = ref([
    {
        label: 'Inicio',
        items: [
            { label: 'Dashboard', icon: 'pi pi-fw pi-home', to: '/dashboard' },
            // { label: 'Posición global', icon: 'pi pi-fw pi-globe', to: '/' }
        ]
    },
    {
        label: 'Mis licencias',
        items: [   
                    {
                        label: 'TPV',
                        icon: 'pi pi-fw pi-desktop',
                        to: '/licencias/tpv'
                    },
                    {
                        label: 'PDA',
                        icon: 'pi pi-fw pi-mobile',
                        to: '/licencias/pda'
                    },
                    {
                        label: 'COCINA',
                        icon: 'pi pi-fw pi-tablet',
                        to: '/licencias/cocina'
                    },
                    {
                        label: 'CASH',
                        icon: 'pi pi-fw pi-money-bill',
                        to: '/licencias/cash'
                    },
                    {
                        label: 'BAND',
                        icon: 'pi pi-fw pi-users',
                        to: '/licencias/band'
                    },
                    {
                        label: 'ONLINE',
                        icon: 'pi pi-fw pi-sync',
                        to: '/licencias/online'
                    },
        ]
    },
    {
        label: 'Mis negocios',
        items: [
                    {
                        label: 'Negocios',
                        icon: 'pi pi-fw pi-building',
                        to: '/business'
                    }
        ]
    }
    // {
    //     label: 'Productos',
    //     items: [
    //         {
    //             label: 'Ver productos',
    //             icon: 'pi pi-fw pi-box',
    //             to: '/documentation'
    //         },
    //     ]
    // },
    // {
    //     label: 'Soporte',
    //     items: [
    //         {
    //             label: 'Ver tickets',
    //             icon: 'pi pi-fw pi-comments',
    //             to: '/documentation'
    //         },
    //     ]
    // },
    // {
    //     label: 'Get started',
    //     items: [
    //         {
    //             label: 'Documentación',
    //             icon: 'pi pi-fw pi-book',
    //             to: '/documentation'
    //         },
    //         {
    //             label: 'Recursos',
    //             icon: 'pi pi-fw pi-folder-open',
    //             to: '/documentation'
    //         },
    //         {
    //             label: 'FAQs',
    //             icon: 'pi pi-fw pi-question',
    //             to: '/documentation'
    //         },
    //     ]
    // }
]);
</script>

<template>
    <ul class="layout-menu">
        <template v-for="(item, i) in model" :key="item">
            <app-menu-item v-if="!item.separator" :item="item" :index="i"></app-menu-item>
            <li v-if="item.separator" class="menu-separator"></li>
        </template>
    </ul>
</template>

<style lang="scss" scoped></style>
