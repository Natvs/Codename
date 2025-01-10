function createGrid() {
    const { width, height, colors } = getUrlParameters();
    const container = document.getElementById('grid-container');
    const cellSize = Math.min(window.innerWidth / width, window.innerHeight / height);

    container.style.width = `${width * cellSize}px`;
    
    for (let i = 0; i < height * width; i++) {
        const cell = document.createElement('div');
        cell.className = `grid-cell color-${colors[i] || 0}`;
        cell.style.width = `${cellSize}px`;
        cell.style.height = `${cellSize}px`;
        container.appendChild(cell);
    }
}

window.onload = createGrid;
window.onresize = createGrid;
