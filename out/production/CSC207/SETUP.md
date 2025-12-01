````markdown
# Project Setup Guide

## Quick Start

### 1. Clone the Repository
```bash
git clone [your-repository-url]
cd CSC207-Project-TUT0401
```

### 2. Set Up Configuration

```bash
cp .env.template .env
```

### 3. Add Your API Keys

Edit the `.env` file and replace the placeholder values with your actual API keys:

```bash
OPEN_ROUTER_API_KEY=your_actual_openrouter_key_here
NINJA_API_KEY=your_actual_ninja_key_here
```

### 4. Run the Project

* **NetBeans**: Open project and click Run
* **Command Line**:

```bash
ant run
```

## Getting API Keys

### OpenRouter API

1. Visit [https://openrouter.ai/](https://openrouter.ai/)
2. Sign up / Log in
3. Go to Settings → API Keys
4. Create a new API key
5. Copy the key (starts with `sk-or-`)
6. Add it to your `.env` file

### Ninja API

1. Visit [https://api-ninjas.com/](https://api-ninjas.com/)
2. Create an account
3. Go to Account → API Keys
4. Copy your API key
5. Add it to your `.env` file

## File Structure

```text
CSC207-Project_final/
├── .env.template     # Template (safe to commit)
├── .env              # Your keys (DO NOT COMMIT)
├── src/              # Source code
└── build.xml         # Build configuration
```

## Security Notice

* Never commit your `.env` file to Git
* Never share your API keys publicly
* The `.env` file is automatically ignored by Git

## Troubleshooting

### Configuration Errors

* Make sure `.env` file exists in the project root
* Verify keys are formatted correctly (no quotes, no spaces around `=`)
* Example of correct format:

```bash
OPEN_ROUTER_API_KEY=sk-or-xxxxxxxxxxxxxxxx
NINJA_API_KEY=yyyyyyyyyyyyyyyy
```

### Build Issues

```bash
ant clean
ant compile
```


