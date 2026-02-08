# ASCII Cats 🐱

ASCII Cats is a Scala-based ASCII art cat image generator. It fetches images from the [CATAAS API](https://cataas.com) and converts them into ASCII art images. The project currently works as a console application or a REST API.

---

## Features

- Generate ASCII cats with customizable width.
- Apply filters:
  - Brightness adjustment
  - Invert colors
  - Flip horizontally or vertically
- Choose ASCII conversion tables:
  - Bourke
  - Long Bourke
  - Custom table
- Clean, functional-style Scala implementation
- REST API endpoint for on-demand ASCII cat generation
- Easily extendable for additional filters or converters

---

## REST API

**Endpoint:** `GET /cat`

**Query Parameters:**

| Parameter        | Type     | Default | Description                                         |
|-----------------|---------|---------|-----------------------------------------------------|
| `width`          | Int     | 120     | Width of the ASCII image                             |
| `brightness`     | Int     | None    | Brightness adjustment (0–255)                        |
| `invert`         | Boolean | false   | Invert colors                                       |
| `flip`           | String  | None    | Flip the image horizontally (`x`) or vertically (`y`) |
| `conversionTable`| String  | Bourke  | Choose conversion table: `Bourke` or `Long Bourke` |
| `customTable`    | String  | None    | Provide a custom ASCII conversion table             |

**Example:**

```bash
curl "http://localhost:8080/cat?width=150&brightness=200&invert=true&flip=x&conversionTable=Bourke"
```

**Error handling:**
Invalid or conflicting parameters return a list of errors.

## Console Usage
You can run the ASCII Cats generator directly from the command line by passing arguments.

Build the fat JAR first:

```bash
sbt assembly
```

Run the app:

```bash
java -jar target/scala-3.4.2/ascii-cats-assembly-1.0.0.jar [options]
```

**Available options**

| Option             | Parameter| Default | Description                                           |
|--------------------|----------|---------|-------------------------------------------------------|
| `--width`           | Int      | 120     | Width of the ASCII image                              |
| `--brightness`      | Int      | None    | Brightness adjustment (0–255)                         |
| `--invert`          | -        | None    | Invert colors                                         |
| `--flip`            | String   | None    | Flip the image horizontally (`x`) or vertically (`y`) |
| `--conversion-table`| String   | Bourke  | Choose conversion table: `Bourke` or `Long Bourke`    |
| `--custom-able`     | String   | None    | Provide a custom ASCII conversion table               |
| `--output-console`  | -        | None    | Output the ASCII image into the console               |
| `--output-file`     | String   | None    | Output the ASCII image into the file specified by the path parameter |

## Contributing

Feel free to open issues or pull requests. Contributions are welcome for:

- Adding new filters or ASCII conversion tables
- Improving API or CLI functionality
-	Refactoring and performance improvements

---

## License

This project is open-source. Use, modify, and distribute freely.


