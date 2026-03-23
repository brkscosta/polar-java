package sh.polar.sdk.models.order;

import sh.polar.sdk.models.customer.PolarCustomerResponse;
import sh.polar.sdk.models.product.PolarProductResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import sh.polar.sdk.models.common.PolarAddress;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents an order response from Polar API.
 * Used for GET /orders/ endpoint responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PolarOrderResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("created_at") OffsetDateTime createdAt,
        @JsonProperty("modified_at") OffsetDateTime modifiedAt,
        @JsonProperty("status") String status,
        @JsonProperty("paid") Boolean paid,
        @JsonProperty("subtotal_amount") Integer subtotalAmount,
        @JsonProperty("discount_amount") Integer discountAmount,
        @JsonProperty("net_amount") Integer netAmount,
        @JsonProperty("tax_amount") Integer taxAmount,
        @JsonProperty("total_amount") Integer totalAmount,
        @JsonProperty("applied_balance_amount") Integer appliedBalanceAmount,
        @JsonProperty("due_amount") Integer dueAmount,
        @JsonProperty("refunded_amount") Integer refundedAmount,
        @JsonProperty("refunded_tax_amount") Integer refundedTaxAmount,
        @JsonProperty("currency") String currency,
        @JsonProperty("billing_reason") String billingReason,
        @JsonProperty("billing_name") String billingName,
        @JsonProperty("billing_address") PolarAddress billingAddress,
        @JsonProperty("invoice_number") String invoiceNumber,
        @JsonProperty("is_invoice_generated") Boolean isInvoiceGenerated,
        @JsonProperty("seats") Integer seats,
        @JsonProperty("customer_id") UUID customerId,
        @JsonProperty("product_id") UUID productId,
        @JsonProperty("discount_id") UUID discountId,
        @JsonProperty("subscription_id") UUID subscriptionId,
        @JsonProperty("checkout_id") UUID checkoutId,
        @JsonProperty("metadata") Map<String, Object> metadata,
        @JsonProperty("custom_field_data") Map<String, Object> customFieldData,
        @JsonProperty("platform_fee_amount") Integer platformFeeAmount,
        @JsonProperty("platform_fee_currency") String platformFeeCurrency,
        @JsonProperty("customer") PolarCustomerResponse customer,
        @JsonProperty("product") PolarProductResponse product,
        @JsonProperty("items") List<PolarOrderItem> items,
        @JsonProperty("description") String description
) {
}
