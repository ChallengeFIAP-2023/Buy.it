import { Flex } from '@global/styles';
import { TextIndicator } from '@screens/QuotesHistory/QuotesList/styles';
import styled from 'styled-components/native';

export const Header = styled.View`
  justify-content: space-between;
  align-items: center;
  display: flex;
  flex-direction: row;
  gap: 0;
  margin: 30px 0;
`;

export const Title = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
  font-size: ${({ theme }) => theme.FONT_SIZE.XL}px;  
  margin-top: 30px;  
  margin-bottom: 10px;
`;

export const QuotesWrapper = styled.View`
  background-color: ${({ theme }) => theme.COLORS.GRAY_500};
  padding: 16px;
  border-radius: 10px;
`;

export const Indicator = styled(TextIndicator)`
  margin: 30px 0;
`;

export const Actions = styled(Flex)`
  margin-top: 20px;
`;

export const NotificationButton = styled.TouchableOpacity`
  position: absolute;
  top: -10px;
  right: 30px;
`