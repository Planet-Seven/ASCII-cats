const apiBase = "/cat";

const tableSelect = document.getElementById("tableSelect");
const customTableInput = document.getElementById("customTable");
const generateBtn = document.getElementById("generate");
const copyBtn = document.getElementById("copy");
const asciiPre = document.getElementById("asciiCat");

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

    generateBtn.disabled = true;
    generateBtn.textContent = "Generating...";
    asciiPre.textContent = "Generating your cat...";

    try {
        const res = await fetch(`${apiBase}?${params}`);
        const text = await res.text();
        asciiPre.textContent = text;
    } catch (err) {
        asciiPre.textContent = "Something went wrong.";
        console.error(err);
    } finally {
        generateBtn.disabled = false;
        generateBtn.textContent = "Generate";
    }

    //copy to clipboard button
    copyBtn.addEventListener("click", () => {
        const text = asciiPre.textContent;
        if (!text || text.includes("Your ASCII cat") || text.includes("Generating")) return;
    
        navigator.clipboard.writeText(text).then(() => {
            copyBtn.textContent = "Copied!";
            setTimeout(() => copyBtn.textContent = "Copy", 1500);
        }).catch(err => {
            console.error("Copy failed:", err);
            copyBtn.textContent = "Failed";
            setTimeout(() => copyBtn.textContent = "Copy", 1500);
        });
    });
});