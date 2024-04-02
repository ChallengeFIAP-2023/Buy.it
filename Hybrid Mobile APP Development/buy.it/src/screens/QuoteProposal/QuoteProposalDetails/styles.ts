import styled from 'styled-components/native';

export const CompanyWrapper = styled.View`
  flex-direction: row;
  align-items: center;
  gap: 16px;

  padding: 50px 30px 20px;
`;

export const CompanyData = styled.View`
  flex-direction: column;
  align-items: start;
`;

export const CompanyName = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.XL}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};

  margin-bottom: 12px;

  max-width: 240px;
  overflow: hidden;
`;

export const CompanyDocument = styled.Text`
  color: ${({ theme }) => theme.COLORS.GRAY_300};
  font-size: ${({ theme }) => theme.FONT_SIZE.SM}px;
`;

export const ProductDetails = styled.View`
  justify-content: center;
  align-items: center;
  gap: 10px;

  margin-top: 16px;
`;

const SimpleText = styled.Text`
  color: ${({ theme }) => theme.COLORS.GRAY_300};
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
`;

export const DescriptionContainer = styled.View`
  padding: 0 30px 20px;

  gap: 16px;
`;

export const Description = styled(SimpleText)``;
export const ProductQuantity = styled(SimpleText)``;
export const PerText = styled(SimpleText)``;

export const ProductName = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.XXL}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
`;

export const TagWrapper = styled.View`
  flex-direction: row;
  gap: 5px;
  align-items: center;
  flex-wrap: wrap;
  overflow: hidden;

  max-width: 300px;
  max-height: 100px;
`;

export const ProductPriceContainer = styled.View`
  align-items: baseline;
  flex-direction: row;
  gap: 10px;
`;

export const Price = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.XXL}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
`;

export const DeliveryContainer = styled.View`
  align-items: center;
  flex-direction: row;
  gap: 10px;
  margin-top: 16px;
`;

export const DeliveryText = styled(SimpleText)`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
`;

export const ActionsButton = styled.View`
  width: 100%;

  padding-bottom: 20px;

  position: absolute;
  bottom: 0;

  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
`;

export const ActionButton = styled.TouchableOpacity.attrs({
  activeOpacity: 0.6,
})`
  width: 80px;
  height: 80px;

  border-radius: 40px;

  align-items: center;
  justify-content: center;
`;

export const Refuse = styled(ActionButton)`
  background-color: ${({ theme }) => theme.COLORS.RED};
`;

export const Accept = styled(ActionButton)`
  background-color: ${({ theme }) => theme.COLORS.GREEN_500};
`;