# **Python+AI - Microsoft**

# **Description**

This folder serves as a **record of participation in the official Microsoft Python+AI course**. These are Python examples using GitHub, OpenAI, Azure OpenAI, and other providers. Projects demonstrate interacting with language models, maintaining context, guiding learning, and filtering content for safety.

Includes three main projects:  

1. **Memory Chat Client**  
2. **Guided Learning AI**  
3. **Safe Memory Chat**
4. **RAG Query Rewrite Chatbot**
5. **PDF and Multimodal Image Analysis**

# **Projects**

## **1. Memory Chat Client**

Assistant that remembers previous user and system messages and maintains context. Supports multiple providers and streaming responses.  

**Main file:** `memory_chat_client`  

---

## **2. Guided Learning AI**

Helps students by giving hints or clues instead of full answers. Ideal and based for guided learning.  

**Main file:** `guided_learning_ai`  

---

## **3. Safe Memory Chat**

Assistant that blocks inappropriate questions or responses using local rules and provider moderation.  

**Main file:** `safe_memory_chat.py`  

---

## **4. RAG Query Rewrite Chatbot**

Demonstrates a Retrieval-Augmented Generation (RAG) approach combined with query rewriting to improve information retrieval accuracy from a given CSV file.
The system rewrites user questions into optimized keyword queries, performs a local Lunr search on the dataset, and then uses a language model to generate responses based strictly on retrieved results.

**Main file:** `rag_query_rewrite.py`

---

## **5. PDF and Multimodal Image Analysis**

Includes scripts for processing PDFs and images with multimodal AI models:  

- **`openai_pdf_analysis.py`** – Converts PDF pages to images and sends them to a multimodal AI model to extract text or answer questions about the content. Useful for automation, catalogs, manuals, or books.  
- **`multimodal_image_ai_analysis.py`** – Sends images (local or remote) to a multimodal AI model for analysis, generating captions, insights, or answering specific questions. Supports Base64-embedded images for offline/local files.

---

## 6. **Office Document Automation & Structured AI Tasks**

- Automates reading and analyzing Office documents and other structured tasks. Supports structured output using Pydantic models and printing results in a readable format with Rich.
- Reads Word (.docx), Excel (.xlsx), and PowerPoint (.pptx) files and converts their content to text.
- Automates multiple tasks in sequence, such as:
    - Extracting event information
    - Checking delivery queries
    - Summarizing Office documents
- Handles local documents and limits large text for AI processing.

**Main file:** `intelligent_office_document_analysis_and_structured_output_pipeline.py`

---

# **Requirements**

- Python 3.9+   
- Environment variables configured for each backend (`API_HOST`, `AZURE_OPENAI_ENDPOINT`, `GITHUB_TOKEN`, etc.)  

---

## Note
© 2025 David Abarca. For portfolio purposes only. Viewing is allowed; reproduction or modification is not permitted.
