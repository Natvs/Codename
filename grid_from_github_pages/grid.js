function getUrlParameters() {
    const params = new URLSearchParams(window.location.search);
    return {
        width: parseInt(params.get('width')) || 10,
        height: parseInt(params.get('height')) || 10,
        colors: (params.get('colors') || '').split(',').map(c => parseInt(c))
    };
}

function createGrid() {
    const { width, height, colors } = getUrlParameters();
    const container = document.getElementById('grid-container');
    const cellSize = Math.min(window.innerWidth / width, window.innerHeight / height);

    container.style.gridTemplateColumns = `repeat(${width}, ${cellSize}px)`;
    
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
