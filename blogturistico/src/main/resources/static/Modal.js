
        function openEditModal(btn) {
            document.getElementById('edit-id').value = btn.getAttribute('data-id');
            document.getElementById('edit-nombre').value = btn.getAttribute('data-nombre');
            document.getElementById('edit-descripcion').value = btn.getAttribute('data-descripcion');
            document.getElementById('edit-ubicacion').value = btn.getAttribute('data-ubicacion');
            document.getElementById('edit-inicio').value = btn.getAttribute('data-inicio');
            document.getElementById('edit-fin').value = btn.getAttribute('data-fin');
            document.getElementById('edit-usuario').value = btn.getAttribute('data-usuario');

            document.getElementById('editModal').style.display = 'block';
        }

        function closeEditModal() {
            document.getElementById('editModal').style.display = 'none';
        }

        function openDeleteModal(url) {
            document.getElementById('confirmDeleteBtn').setAttribute('href', url);
            document.getElementById('deleteModal').style.display = 'block';
        }

        function closeDeleteModal() {
            document.getElementById('deleteModal').style.display = 'none';
        }

        window.onclick = function(event) {
            var editModal = document.getElementById('editModal');
            var deleteModal = document.getElementById('deleteModal');
            if (event.target == editModal) {
                closeEditModal();
            }
            if (event.target == deleteModal) {
                closeDeleteModal();
            }
        }