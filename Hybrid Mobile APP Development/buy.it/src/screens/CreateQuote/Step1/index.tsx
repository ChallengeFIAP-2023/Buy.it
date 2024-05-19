import { useLayoutEffect, useState } from 'react';
import { ArrowRight, Plus } from 'phosphor-react-native';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import Toast from 'react-native-toast-message';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { CreateQuoteRoutes } from '..';
import { TFlatList } from 'react-native-input-select/lib/typescript/types/index.types';

// Validation import
import { Controller, SubmitHandler, useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { Step1FormSchema } from '@validations/QuoteDetails';

// Theme import
import theme from '@theme/index';

// Component import
import {
  Button,
  CustomDropdown,
  DecreasingContainer,
  DefaultComponent,
  Input,
  WrapperPage,
} from '@components/index';

// Hooks import
import { useQuote } from '@hooks/useQuote';

// Style import
import { ScrollableContent, Fieldset } from '@global/styles/index';
import { CustomModal } from '@components/Modal';
import { CreateTagContainer } from './styles';

interface Step1Form {
  idsTags: number[];
  idDepartamento: number;
}

export const Step1: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<CreateQuoteRoutes, 'Step1'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // State
  const [isModalVisible, setModalVisible] = useState(false);
  const [tagName, setTagName] = useState('');
  const [newTagError, setNewTagError] = useState<string | undefined>();

  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<Step1Form>({
    resolver: yupResolver(Step1FormSchema),
  });

  const toggleModal = () => setModalVisible(previousState => !previousState);

  const {
    setProduct,
    tags,
    departments,
    handleNewTag,
    fetchDepartmentsAndTags,
  } = useQuote();

  useLayoutEffect(() => {
    fetchDepartmentsAndTags();
  }, []);

  const newTag = () => {
    if (!tagName) return setNewTagError('Nome da tag é obrigatório!');

    handleNewTag({ nome: tagName });

    toggleModal();

    return Toast.show({
      type: 'success',
      text1: 'Tag criada com sucesso',
    });
  };

  const onSubmit: SubmitHandler<Step1Form> = data => {
    setProduct(prevProd => ({ ...prevProd, ...data }));
    return navigation.navigate('Step2');
  };

  const departmentsOptions: TFlatList = departments.map(item => ({
    label: item.nome,
    value: item.id,
  }));

  const tagsOptions: TFlatList = tags.map(tag => ({
    label: tag.nome,
    value: tag.id,
  }));

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            title: 'De que tipo de produto você precisa hoje?',
            subtitle: 'Passo 1 de 4',
          }}
          key="default-component-quote-details"
        />

        <DecreasingContainer>
          <Fieldset>
            <Controller
              control={control}
              name="idDepartamento"
              render={({ field: { value, onChange } }) => (
                <CustomDropdown
                  isSearchable
                  label="Departamento"
                  placeholder="Selecione um departamento"
                  options={departmentsOptions}
                  selectedValue={value}
                  onValueChange={onChange}
                  error={errors.idDepartamento?.message}
                  listControls={{
                    emptyListMessage: 'Nenhum departamento encontrado.',
                  }}
                />
              )}
            />
          </Fieldset>

          <Fieldset>
            <Controller
              control={control}
              name="idsTags"
              render={({ field: { value, onChange } }) => (
                <CustomDropdown
                  label="Tags relacionadas"
                  isSearchable
                  isMultiple
                  placeholder="Selecione as tags relacionadas"
                  options={tagsOptions}
                  selectedValue={value}
                  onValueChange={onChange}
                  error={errors.idsTags?.message}
                  listFooterComponent={
                    <CreateTagContainer>
                      <Button
                        label="Cadastrar tag"
                        onPress={toggleModal}
                        icon={<Plus color={theme.COLORS.WHITE} weight="bold" />}
                      />
                    </CreateTagContainer>
                  }
                />
              )}
            />
          </Fieldset>
        </DecreasingContainer>
      </ScrollableContent>

      <CustomModal
        modalProps={{ isVisible: isModalVisible }}
        title="Nova tag"
        subtitle="Tags nos ajudam a selecionar os melhores fornecedores para a cotação"
        onClose={toggleModal}
      >
        <Fieldset>
          <Input
            value={tagName}
            onChangeText={value => setTagName(value)}
            placeholder="Ex.: Papelaria"
            error={newTagError}
          />
        </Fieldset>

        <Button label="Criar" size="LG" onPress={() => newTag()} />
      </CustomModal>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={handleSubmit(onSubmit)}
      />
    </WrapperPage>
  );
};
