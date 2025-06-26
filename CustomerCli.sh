#!/bin/bash

BASE_URL="http://localhost:8080/api/customers"

while true; do
  echo ""
  echo "=== Customer CLI ==="
  echo "1. Create Customer"
  echo "2. Get Customer by ID"
  echo "3. Exit"
  read -p "Choose option: " choice

  case $choice in
    1)
      read -p "First Name: " firstName
      read -p "Last Name: " lastName
      read -p "Email Address: " email

      json=$(jq -n \
        --arg fn "$firstName" \
        --arg ln "$lastName" \
        --arg email "$email" \
        '{firstName: $fn, lastName: $ln, emailAddress: $email}')

      echo "Sending request..."
      curl -s -X POST "$BASE_URL" \
        -H "Content-Type: application/json" \
        -d "$json" \
        -w "\nStatus: %{http_code}\n"
      ;;
    2)
      read -p "Enter Customer ID: " id
      curl -s -X GET "$BASE_URL/$id" -w "\nStatus: %{http_code}\n"
      ;;
    3)
      echo "Exiting..."
      exit 0
      ;;
    *)
      echo "Invalid option"
      ;;
  esac
done
