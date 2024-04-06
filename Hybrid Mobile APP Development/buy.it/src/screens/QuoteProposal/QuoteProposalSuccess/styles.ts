import styled from 'styled-components/native';

export const Container = styled.View`
  padding: 0 20px;
  padding-bottom: 200px;
`;

export const DescriptionContainer = styled.View`
  justify-content: center;
  align-items: center;
  gap: 16px;
`;

export const CompanyWrapper = styled.View`
  flex-direction: column;
  align-items: center;
  gap: 16px;

  margin-top: 40px;
`;

export const CompanyData = styled.View`
  flex-direction: column;
  align-items: start;
  gap: 5px;

  padding-bottom: 15px;

  border-bottom-width: 3px;
  border-bottom-color: ${({ theme }) => theme.COLORS.GRAY_600};
`;

export const CompanyName = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};

  text-transform: capitalize;
`;

export const CompanyDocument = styled.Text`
  color: ${({ theme }) => theme.COLORS.GRAY_300};
  font-size: ${({ theme }) => theme.FONT_SIZE.SM}px;

  text-align: center;
`;

export const SectionProductDetail = styled.View`
  justify-content: center;
  align-items: start;
  gap: 10px;

  margin-top: 20px;
`;

export const SectionLabel = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.XL}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.REGULAR};
  letter-spacing: -0.5px;
`;

const SimpleText = styled.Text`
  color: ${({ theme }) => theme.COLORS.GRAY_300};
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
`;

export const SectionValueText = styled(SimpleText)``;

export const Description = styled(SimpleText)`
  text-align: center;
`;

export const CongratulationText = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.XXL}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
  text-align: center;
`;

export const TagWrapper = styled.View`
  flex-direction: row;
  gap: 5px;
  align-items: center;
  flex-wrap: wrap;
  overflow: hidden;

  max-height: 100px;
`;

export const ActionsButton = styled.View`
  width: 100%;

  padding: 20px 0;

  position: absolute;
  bottom: 0;

  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;

  background-color: ${({ theme }) => theme.COLORS.GRAY_600};
`;

export const ActionButton = styled.TouchableOpacity.attrs({
  activeOpacity: 0.6,
})`
  width: 60px;
  height: 60px;

  border-radius: 30px;

  align-items: center;
  justify-content: center;
  background-color: ${({ theme }) => theme.COLORS.PRIMARY};
`;
