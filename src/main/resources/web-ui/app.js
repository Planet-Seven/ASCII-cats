const apiBase = "/cat";

const tableSelect = document.getElementById("tableSelect");
const customTableInput = document.getElementById("customTable");

tableSelect.addEventListener("change", () => {
    if (tableSelect.value === "Custom") {
        customTableInput.style.display = "inline-block";
    } else {
        customTableInput.style.display = "none";
    }
});

document.getElementById("generate").addEventListener("click", async () => {
    const params = new URLSearchParams({
        width: document.getElementById("width").value,
        brightness: document.getElementById("brightness").value,
        invert: document.getElementById("invert").checked,
    });

    if (tableSelect.value === "Bourke" || tableSelect.value === "LongBourke") {
        params.set("conversionTable", tableSelect.value);
    } else if (tableSelect.value === "Custom" && customTableInput.value.trim() !== "") {
        params.set("customTable", customTableInput.value);
    }

    const res = await fetch(`${apiBase}?${params}`);
    const text = await res.text();
    document.getElementById("asciiCat").textContent = text;
});