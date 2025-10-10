# **Python+AI - Microsoft**

# **Description**

This folder serves as a **record of participation in the official Microsoft Python+AI course**. These are Python examples using GitHub, OpenAI, Azure OpenAI, and other providers. Projects demonstrate interacting with language models, maintaining context, guiding learning, and filtering content for safety.

Includes three main projects:  

1. **Memory Chat Client**  
2. **Guided Learning AI**  
3. **Safe Memory Chat**
4. **RAG Query Rewrite Chatbot**

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

# **Requirements**

- Python 3.9+   
- Environment variables configured for each backend (`API_HOST`, `AZURE_OPENAI_ENDPOINT`, `GITHUB_TOKEN`, etc.)  

---

## Note
© 2025 David Abarca. For portfolio purposes only. Viewing is allowed; reproduction or modification is not permitted.
