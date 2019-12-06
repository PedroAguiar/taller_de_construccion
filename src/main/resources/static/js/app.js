(function(){
    const searchBox = document.getElementById('search-box');
    const searchForm = document.getElementById('search-form');

    searchForm.addEventListener('submit', (e) => {
        e.preventDefault();
        window.location.href = searchBox.value ? `/?q=${searchBox.value}` : '/';
    })
})();